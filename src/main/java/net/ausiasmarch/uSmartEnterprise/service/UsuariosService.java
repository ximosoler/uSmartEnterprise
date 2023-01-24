package net.ausiasmarch.uSmartEnterprise.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import net.ausiasmarch.uSmartEnterprise.entity.EmpresaEntity;
import net.ausiasmarch.uSmartEnterprise.entity.UsuariosEntity;
import net.ausiasmarch.uSmartEnterprise.exception.CannotPerformOperationException;
import net.ausiasmarch.uSmartEnterprise.exception.ResourceNotFoundException;
import net.ausiasmarch.uSmartEnterprise.exception.ResourceNotModifiedException;
import net.ausiasmarch.uSmartEnterprise.helper.RandomHelper;
import net.ausiasmarch.uSmartEnterprise.helper.ValidationHelper;
import net.ausiasmarch.uSmartEnterprise.repository.EmpresaRepository;
import net.ausiasmarch.uSmartEnterprise.repository.TipodeCuentaRepository;
import net.ausiasmarch.uSmartEnterprise.repository.UsuariosRepository;

@Service
public class UsuariosService {

    @Autowired
    UsuariosRepository oUsuariosRepository;
    @Autowired
    EmpresaService oEmpresaService;
    @Autowired
    EmpresaRepository oEmpresaRepository;
    @Autowired
    TareasService oTareasService;
    @Autowired
    TipodeCuentaService oTipodeCuentaService;
    @Autowired
    TipodeCuentaRepository oTipodeCuentaRepository;

    private final List<String> nombre = List.of("Ainhoa", "Kevin", "Estefania", "Cristina",
            "Jose Maria", "Lucas Ezequiel", "Carlos", "Elliot", "Alexis", "Ruben", "Luis Fernando", "Karim", "Luis",
            "Jose David", "Nerea", "Ximo", "Iris", "Alvaro", "Mario", "Raimon");

    private final List<String> apellido = List.of("Benito", "Blanco", "Boriko", "Carrascosa", "Guerrero", "Gyori",
            "Lazaro", "Luque", "Perez", "Perez", "Perez", "Rezgaoui", "Rodríguez", "Rosales", "Soler", "Soler", "Suay", "Talaya", "Tomas", "Vilar");
    

    private final List<String> empleo = List.of("Reparación","Venta","Reunión","Brainstorming");


    public void validate(Long id) {
        if (!oUsuariosRepository.existsById(id)) {
            throw new ResourceNotFoundException("id " + id + " not exist");
        }
    }

    public void validate(UsuariosEntity oUsuarioEntity) {
        ValidationHelper.validateStringLength(oUsuarioEntity.getNombre(), 2, 50, "campo nombre de Usuario (el campo debe tener longitud de 2 a 50 caracteres)");
        ValidationHelper.validateStringLength(oUsuarioEntity.getApellidos(), 2, 50, "campo primer apellido de Usuario (el campo debe tener longitud de 2 a 50 caracteres)");
        ValidationHelper.validateEmail(oUsuarioEntity.getEmail(), " campo email de Usuario");
        ValidationHelper.validateStringLength(oUsuarioEntity.getEmpleo(), 2, 50, "campo nombre de Usuario (el campo debe tener longitud de 2 a 50 caracteres)");
        oEmpresaService.validate(oUsuarioEntity.getEmpresa().getId()); 
        oTipodeCuentaService.validate(oUsuarioEntity.getTipodeCuenta().getId());
     
       
    }

    public UsuariosEntity get(Long id) {
        //oAuthService.OnlyAdmins();
        return oUsuariosRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Usuarios with id: " + id + " not found"));
    }


    public Long count() {
        /* oAuthService.OnlyAdmins(); */
        return oUsuariosRepository.count();
    }


    public Long create(UsuariosEntity oNewUsuariosEntity) {
/*         oAuthService.OnlyAdmins();*/
        validate(oNewUsuariosEntity);
        oNewUsuariosEntity.setId(0L);
        return oUsuariosRepository.save(oNewUsuariosEntity).getId();
    }

    public Long delete(Long id) {
        //oAuthService.OnlyAdmins();
        validate(id);
        oUsuariosRepository.deleteById(id);
        if (oUsuariosRepository.existsById(id)) {
            throw new ResourceNotModifiedException("can't remove register " + id);
        } else {
            return id;
        }
    }

    public Long update(UsuariosEntity oUsuariosEntity) {
        validate(oUsuariosEntity.getId());
        //oAuthService.OnlyAdmins();
        return oUsuariosRepository.save(oUsuariosEntity).getId();
    }

    public UsuariosEntity generateOne() {
        //oAuthService.OnlyAdmins();
        return oUsuariosRepository.save(generateUsuarios());
    }

    public UsuariosEntity getOneRandom() {
        if (count() > 0) {
            UsuariosEntity oUsuariosEntity = null;
            int iPosicion = RandomHelper.getRandomInt(0, (int) oUsuariosRepository.count() - 1);
            Pageable oPageable = PageRequest.of(iPosicion, 1);
            Page<UsuariosEntity> UsuariosPage = oUsuariosRepository.findAll(oPageable);
            List<UsuariosEntity> UsuariosList = UsuariosPage.getContent();
            oUsuariosEntity = oUsuariosRepository.getById(UsuariosList.get(0).getId());
            return oUsuariosEntity;
        } else {
            throw new CannotPerformOperationException("ho hay usuarios en la base de datos");
        }
    }

    private UsuariosEntity generateUsuarios() {
        UsuariosEntity oUsuariosEntity = new UsuariosEntity();

        oUsuariosEntity.setNombre(nombre.get(RandomHelper.getRandomInt(0, nombre.size() - 1)));
        oUsuariosEntity.setApellidos(apellido.get(RandomHelper.getRandomInt(0, apellido.size() - 1)));
        oUsuariosEntity.setEmail(oUsuariosEntity.getNombre() + RandomHelper.getRandomInt(0, 1000) + "@ausias.net");
        oUsuariosEntity.setEmpleo(empleo.get(RandomHelper.getRandomInt(0, empleo.size() - 1)));
        oUsuariosEntity.setTipodeCuenta(oTipodeCuentaRepository.getById((long) 2));
        List <EmpresaEntity> totalTeams = oEmpresaRepository.findAll();
        int randomTeamId = RandomHelper.getRandomInt(1, totalTeams.size()-1);
        EmpresaEntity oEmpresaEntity = totalTeams.get(randomTeamId);
        oUsuariosEntity.setEmpresa(oEmpresaEntity);

        return oUsuariosEntity;
    }

    public UsuariosEntity generate() {
        /* oAuthService.OnlyAdmins(); */
        return generateUsuarios();
    }

    public Long generateSome(Integer amount) {
        /* oAuthService.OnlyAdmins(); */
        List<UsuariosEntity> userList = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            UsuariosEntity oUsuarioEntity = generateUsuarios();
            oUsuariosRepository.save(oUsuarioEntity);
            userList.add(oUsuarioEntity);
        }
        return oUsuariosRepository.count();
    }


    public Page<UsuariosEntity> getPage(Pageable oPageable, String strFilter, Long idEmpresa, Long idTipodeCuenta) {
        //oAuthService.OnlyAdmins();
        ValidationHelper.validateRPP(oPageable.getPageSize());
        if (strFilter == null || strFilter.length() == 0) {
            if (idEmpresa == null) {
                if (idTipodeCuenta == null) {
                    return oUsuariosRepository.findAll(oPageable);
                } else {
                    return oUsuariosRepository.findByTipodeCuenta(idTipodeCuenta, oPageable);
                }
            } else {
                if (idTipodeCuenta == null) {
                    return oUsuariosRepository.findByEmpresa(idEmpresa, oPageable);
                } else {
                    return oUsuariosRepository.findByEmpresaAndTipodeCuenta(idEmpresa, idTipodeCuenta, oPageable);
                }
            }
        } else {
            if (idEmpresa == null) {
                if (idTipodeCuenta == null) {
                    return oUsuariosRepository.findByNombreIgnoreCaseContainingOrApellidosIgnoreCaseContaining(strFilter, strFilter, oPageable);
                } else {
                    return oUsuariosRepository.findByNombreIgnoreCaseContainingOrApellidosIgnoreCaseContainingAndTipodeCuenta(strFilter, strFilter, strFilter, idTipodeCuenta, oPageable);
                }
            } else {
                if (idTipodeCuenta == null) {
                    return oUsuariosRepository.findByNombreIgnoreCaseContainingOrApellidosIgnoreCaseContainingaAndEmpresa(strFilter, strFilter, strFilter, idEmpresa, oPageable);
                } else {
                    return oUsuariosRepository.findByNombreIgnoreCaseContainingOrApellidosIgnoreCaseContainingaAndEmpresaAndTipodeCuenta(strFilter, strFilter, strFilter, idEmpresa, idTipodeCuenta, oPageable);
                }
            }
        }

    }


}
