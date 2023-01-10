package net.ausiasmarch.uSmartEnterprise.service;

import org.springframework.stereotype.Service;

import net.ausiasmarch.uSmartEnterprise.entity.TareasEntity;
import net.ausiasmarch.uSmartEnterprise.exception.ResourceNotFoundException;
import net.ausiasmarch.uSmartEnterprise.exception.ResourceNotModifiedException;
import net.ausiasmarch.uSmartEnterprise.exception.ValidationException;
import net.ausiasmarch.uSmartEnterprise.helper.ValidationHelper;
import net.ausiasmarch.uSmartEnterprise.repository.TareasRepository;

@Service
public class TareasService {
    TareasRepository oTareasRepository;
   

    public void validate(Long id) {
        if (!oTareasRepository.existsById(id)) {
            throw new ResourceNotFoundException("id " + id + " not exist");
        }
    }

    public void validate(TareasEntity oTareasEntity) {
        ValidationHelper.validateStringLength(oTareasEntity.getNombre(), 2, 50, "campo nombre de la Categoría(el campo debe tener longitud de 2 a 50 caracteres)");
        if (oTareasRepository.existsByNombre(oTareasEntity.getNombre())) {
            throw new ValidationException("el campo username está repetido");
        }
    }

    public TareasEntity get(Long id) {
        //oAuthService.OnlyAdmins();
        return oTareasRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Categorias with id: " + id + " not found"));
    }

    public Long count() {
        //oAuthService.OnlyAdmins();
        return oTareasRepository.count();
    }

    public Long update(TareasEntity oCategoriasEntity) {
        validate(oCategoriasEntity.getId());
        //oAuthService.OnlyAdmins();
        return oTareasRepository.save(oCategoriasEntity).getId();
    }

    public Long create(TareasEntity oNewCategoriasEntity) {
        //oAuthService.OnlyAdmins();
        validate(oNewCategoriasEntity);
        oNewCategoriasEntity.setId(0L);
        return oTareasRepository.save(oNewCategoriasEntity).getId();
    }

    public Long delete(Long id) {
        //oAuthService.OnlyAdmins();
        validate(id);
        oTareasRepository.deleteById(id);
        if (oTareasRepository.existsById(id)) {
            throw new ResourceNotModifiedException("can't remove register " + id);
        } else {
            return id;
        }
    }
    

}

