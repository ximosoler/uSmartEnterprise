package net.ausiasmarch.uSmartEnterprise.service;

import net.ausiasmarch.uSmartEnterprise.entity.CategoriasEntity;
import net.ausiasmarch.uSmartEnterprise.exception.CannotPerformOperationException;
import net.ausiasmarch.uSmartEnterprise.exception.ResourceNotFoundException;
import net.ausiasmarch.uSmartEnterprise.exception.ResourceNotModifiedException;
import net.ausiasmarch.uSmartEnterprise.helper.RandomHelper;
import net.ausiasmarch.uSmartEnterprise.helper.ValidationHelper;
import org.springframework.data.domain.Pageable;
import net.ausiasmarch.uSmartEnterprise.repository.CategoriasRepository;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class CategoriasService {

    @Autowired
    CategoriasRepository oCategoriasRepository;

    private final List<String> nombre = List.of("IT","Marketing", "Ventas", "Mantenimiento");

    public void validate(Long id) {
        if (!oCategoriasRepository.existsById(id)) {
            throw new ResourceNotFoundException("id " + id + " not exist");
        }
    }

    public void validate(CategoriasEntity oCategoriasEntity) {
        ValidationHelper.validateStringLength(oCategoriasEntity.getNombre(), 2, 50, "campo nombre de la Categoría(el campo debe tener longitud de 2 a 50 caracteres)");
        if (oCategoriasRepository.existsByNombre(oCategoriasEntity.getNombre())) {
            throw new ValidationException("el campo username está repetido");
        }
    }

    public CategoriasEntity get(Long id) {
        //oAuthService.OnlyAdmins();
        return oCategoriasRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Categorias with id: " + id + " not found"));
    }

    public Long count() {
        //oAuthService.OnlyAdmins();
        return oCategoriasRepository.count();
    }

    public Long update(CategoriasEntity oCategoriasEntity) {
        validate(oCategoriasEntity.getId());
        //oAuthService.OnlyAdmins();
        return oCategoriasRepository.save(oCategoriasEntity).getId();
    }
    public Long create(CategoriasEntity oNewCategoriasEntity) {
        //oAuthService.OnlyAdmins();
         /* validate(oNewCategoriasEntity); */
        oNewCategoriasEntity.setId(0L);
        return oCategoriasRepository.save(oNewCategoriasEntity).getId();
    }

    public Long delete(Long id) {
        //oAuthService.OnlyAdmins();
        validate(id);
        oCategoriasRepository.deleteById(id);
        if (oCategoriasRepository.existsById(id)) {
            throw new ResourceNotModifiedException("can't remove register " + id);
        } else {
            return id;
        }
    }  

    public Page<CategoriasEntity> getPage(Pageable oPageable, String srtFilter, Long lCategorias) {
        /* oAuthService.OnlyAdminsOrUsers(); */
        ValidationHelper.validateRPP(oPageable.getPageSize());
        Page<CategoriasEntity> oPage = null;
        /* if (oAuthService.isAdmin()) { */
            if (lCategorias != null) {
                oPage = oCategoriasRepository.findByNombre(lCategorias, oPageable);
            } else {
                oPage = oCategoriasRepository.findAll(oPageable);
            }
                return oPage;
    
    }

    public CategoriasEntity generateOne() {
        //oAuthService.OnlyAdmins();
        return oCategoriasRepository.save(generateCategorias());
    }

    public CategoriasEntity getOneRandom() {
        if (count() > 0) {
            CategoriasEntity oCategoriasEntity = null;
            int iPosicion = RandomHelper.getRandomInt(0, (int) oCategoriasRepository.count() - 1);
            Pageable oPageable = PageRequest.of(iPosicion, 1);
            Page<CategoriasEntity> CategoriasPage = oCategoriasRepository.findAll(oPageable);
            List<CategoriasEntity> CategoriasList = CategoriasPage.getContent();
            oCategoriasEntity = oCategoriasRepository.getById(CategoriasList.get(0).getId());
            return oCategoriasEntity;
        } else {
            throw new CannotPerformOperationException("ho hay Categorias en la base de datos");
        }
    }

    private CategoriasEntity generateCategorias() {
        CategoriasEntity oCategoriasEntity = new CategoriasEntity();

        oCategoriasEntity.setNombre(nombre.get(RandomHelper.getRandomInt(0, nombre.size() - 1)));
        

        return oCategoriasEntity;
    }

    public CategoriasEntity generate() {
        /* oAuthService.OnlyAdmins(); */
        return generateCategorias();
    }

    public Long generateSome(Integer amount) {
        /* oAuthService.OnlyAdmins(); */
        List<CategoriasEntity> userList = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            CategoriasEntity oUsuarioEntity = generateCategorias();
            oCategoriasRepository.save(oUsuarioEntity);
            userList.add(oUsuarioEntity);
        }
        return oCategoriasRepository.count();
    }

}
        