package net.ausiasmarch.uSmartEnterprise.service;

import net.ausiasmarch.uSmartEnterprise.entity.CategoriasEntity;
import net.ausiasmarch.uSmartEnterprise.exception.ResourceNotFoundException;
import net.ausiasmarch.uSmartEnterprise.exception.ResourceNotModifiedException;
import net.ausiasmarch.uSmartEnterprise.helper.ValidationHelper;
import net.ausiasmarch.uSmartEnterprise.repository.CategoriasRepository;

import javax.validation.ValidationException;

import org.springframework.stereotype.Service;



@Service
public class CategoriasService {

    CategoriasRepository oCategoriasRepository;
   

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
        validate(oNewCategoriasEntity);
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
}
