package net.ausiasmarch.uSmartEnterprise.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import net.ausiasmarch.uSmartEnterprise.entity.EmpresaEntity;
import net.ausiasmarch.uSmartEnterprise.entity.UsuarioEntity;
import net.ausiasmarch.uSmartEnterprise.exception.CannotPerformOperationException;
import net.ausiasmarch.uSmartEnterprise.exception.ResourceNotFoundException;
import net.ausiasmarch.uSmartEnterprise.exception.ResourceNotModifiedException;
import net.ausiasmarch.uSmartEnterprise.helper.RandomHelper;
import net.ausiasmarch.uSmartEnterprise.helper.ValidationHelper;
import net.ausiasmarch.uSmartEnterprise.repository.EmpresaRepository;
import net.ausiasmarch.uSmartEnterprise.repository.TipodecuentaRepository;
import net.ausiasmarch.uSmartEnterprise.repository.UsuarioRepository;



@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository oUsuarioRepository;
    @Autowired
    EmpresaService oEmpresaService;
    @Autowired
    EmpresaRepository oEmpresaRepository;
    @Autowired
    TareaService oTareaService;
    @Autowired
    TipodecuentaService oTipodecuentaService;
    @Autowired
    TipodecuentaRepository oTipodecuentaRepository;

    private final List<String> nombre = List.of("Ainhoa", "Kevin", "Estefania", "Cristina",
            "Jose Maria", "Lucas Ezequiel", "Carlos", "Elliot", "Alexis", "Ruben", "Luis Fernando", "Karim", "Luis",
            "Jose David", "Nerea", "Ximo", "Iris", "Alvaro", "Mario", "Raimon");

    private final List<String> apellido = List.of("Benito", "Blanco", "Boriko", "Carrascosa", "Guerrero", "Gyori",
            "Lazaro", "Luque", "Perez", "Perez", "Perez", "Rezgaoui", "Rodríguez", "Rosales", "Soler", "Soler", "Suay", "Talaya", "Tomas", "Vilar");
    

    private final List<String> empleo = List.of("Reparación","Venta","Reunión","Brainstorming");


    public void validate(Long id) {
        if (!oUsuarioRepository.existsById(id)) {
            throw new ResourceNotFoundException("id " + id + " not exist");
        }
    }

    public void validate(UsuarioEntity oUsuarioEntity) {
        ValidationHelper.validateStringLength(oUsuarioEntity.getNombre(), 2, 50, "campo nombre de Usuario (el campo debe tener longitud de 2 a 50 caracteres)");
        ValidationHelper.validateStringLength(oUsuarioEntity.getApellidos(), 2, 50, "campo primer apellido de Usuario (el campo debe tener longitud de 2 a 50 caracteres)");
        ValidationHelper.validateEmail(oUsuarioEntity.getEmail(), " campo email de Usuario");
        ValidationHelper.validateStringLength(oUsuarioEntity.getEmpleo(), 2, 50, "campo nombre de Usuario (el campo debe tener longitud de 2 a 50 caracteres)");
        oEmpresaService.validate(oUsuarioEntity.getEmpresa().getId()); 
        oTipodecuentaService.validate(oUsuarioEntity.getTipodecuenta().getId());
     
       
    }


    public UsuarioEntity get(Long id) {
        //oAuthService.OnlyAdmins();
        return oUsuarioRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Usuarios with id: " + id + " not found"));
    }


    public UsuarioService() {
    }


    public Long count() {
        /* oAuthService.OnlyAdmins(); */
        return oUsuarioRepository.count();
    }


    public Long create(UsuarioEntity oNewUsuarioEntity) {
/*         oAuthService.OnlyAdmins();*/
        //validate(oNewUsuariosEntity);
        oNewUsuarioEntity.setId(0L);
        return oUsuarioRepository.save(oNewUsuarioEntity).getId();
    }

    public Long delete(Long id) {
        //oAuthService.OnlyAdmins();
        //validate(id);
        oUsuarioRepository.deleteById(id);
        if (oUsuarioRepository.existsById(id)) {
            throw new ResourceNotModifiedException("can't remove register " + id);
        } else {
            return id;
        }
    }

    public Long update(UsuarioEntity oUsuariosEntity) {
        //validate(oUsuariosEntity.getId());
        //oAuthService.OnlyAdmins();
        return oUsuarioRepository.save(oUsuariosEntity).getId();
    }

    public UsuarioEntity generateOne() {
        //oAuthService.OnlyAdmins();
        return oUsuarioRepository.save(generateUsuarios());
    }

    public UsuarioEntity getOneRandom() {
        if (count() > 0) {
            UsuarioEntity oUsuariosEntity = null;
            int iPosicion = RandomHelper.getRandomInt(0, (int) oUsuarioRepository.count() - 1);
            Pageable oPageable = PageRequest.of(iPosicion, 1);
            Page<UsuarioEntity> UsuariosPage = oUsuarioRepository.findAll(oPageable);
            List<UsuarioEntity> UsuariosList = UsuariosPage.getContent();
            oUsuariosEntity = oUsuarioRepository.getById(UsuariosList.get(0).getId());
            return oUsuariosEntity;
        } else {
            throw new CannotPerformOperationException("ho hay usuarios en la base de datos");
        }
    }

    private UsuarioEntity generateUsuarios() {
        UsuarioEntity oUsuariosEntity = new UsuarioEntity();

        oUsuariosEntity.setNombre(nombre.get(RandomHelper.getRandomInt(0, nombre.size() - 1)));
        oUsuariosEntity.setApellidos(apellido.get(RandomHelper.getRandomInt(0, apellido.size() - 1)));
        oUsuariosEntity.setEmail(oUsuariosEntity.getNombre() + RandomHelper.getRandomInt(0, 1000) + "@ausias.net");
        oUsuariosEntity.setEmpleo(empleo.get(RandomHelper.getRandomInt(0, empleo.size() - 1)));
        oUsuariosEntity.setTipodecuenta(oTipodecuentaRepository.getById((long) 2));
        List <EmpresaEntity> totalTeams = oEmpresaRepository.findAll();
        int randomTeamId = RandomHelper.getRandomInt(1, totalTeams.size()-1);
        EmpresaEntity oEmpresaEntity = totalTeams.get(randomTeamId);
        oUsuariosEntity.setEmpresa(oEmpresaEntity);
        
        return oUsuariosEntity;
    }

    public UsuarioEntity generate() {
        /* oAuthService.OnlyAdmins(); */
        return generateUsuarios();
    }

    public Long generateSome(Integer amount) {
        /* oAuthService.OnlyAdmins(); */
        List<UsuarioEntity> userList = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            UsuarioEntity oUsuarioEntity = generateUsuarios();
            oUsuarioRepository.save(oUsuarioEntity);
            userList.add(oUsuarioEntity);
        }
        return oUsuarioRepository.count();
    }


    /* public Page<UsuarioEntity> getPage(Pageable oPageable, String strFilter, Long idEmpresa, Long idTiposdecuenta) {
        //oAuthService.OnlyAdmins();
        ValidationHelper.validateRPP(oPageable.getPageSize());
        if (strFilter == null || strFilter.length() == 0) {
            if (idEmpresa == null) {
                if (idTiposdecuenta == null) {
                    return oUsuarioRepository.findAll(oPageable);
                } else {
                    return oUsuarioRepository.findByIdtiposdecuenta(idTiposdecuenta, oPageable);
                }
            } else {
                if (idTiposdecuenta == null) {
                    return oUsuarioRepository.findByIdempresa(idEmpresa, oPageable);
                } else {
                    return oUsuarioRepository.findByIdempresaAndIdtiposdecuenta(idEmpresa, idTiposdecuenta, oPageable);
                }
            }
        } else {
            if (idEmpresa == null) {
                if (idTiposdecuenta == null) {
                    return oUsuarioRepository.findByNombreIgnoreCaseContainingOrApellidosIgnoreCaseContaining(strFilter, strFilter, oPageable);
                } else {
                    return oUsuarioRepository.findByNombreIgnoreCaseContainingOrApellidosIgnoreCaseContainingAndIdtiposdecuenta(strFilter, strFilter, idTiposdecuenta, oPageable);
                }
            } else {
                if (idTiposdecuenta == null) {
                    return oUsuarioRepository.findByNombreIgnoreCaseContainingOrApellidosIgnoreCaseContainingAndIdempresa(strFilter, strFilter, idEmpresa, oPageable);
                } else {
                    return oUsuarioRepository.findByNombreIgnoreCaseContainingOrApellidosIgnoreCaseContainingAndIdempresaAndIdtiposdecuenta(strFilter, strFilter, idEmpresa, idTiposdecuenta, oPageable);
                }
            }
        }

    } */
   
}
