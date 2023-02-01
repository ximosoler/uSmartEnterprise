package net.ausiasmarch.uSmartEnterprise.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.ausiasmarch.uSmartEnterprise.entity.TareaEntity;
import net.ausiasmarch.uSmartEnterprise.exception.ResourceNotFoundException;
import net.ausiasmarch.uSmartEnterprise.exception.ResourceNotModifiedException;
import net.ausiasmarch.uSmartEnterprise.exception.ValidationException;
import net.ausiasmarch.uSmartEnterprise.helper.ValidationHelper;
import net.ausiasmarch.uSmartEnterprise.repository.TareaRepository;


@Service
public class TareaService {

    @Autowired
    TareaRepository oTareaRepository;
   

    public void validate(Long id) {
        if (!oTareaRepository.existsById(id)) {
            throw new ResourceNotFoundException("id " + id + " not exist");
        }
    }

    public void validate(TareaEntity oTareasEntity) {
        ValidationHelper.validateStringLength(oTareasEntity.getNombre(), 2, 50, "campo nombre de la Categoría(el campo debe tener longitud de 2 a 50 caracteres)");
        if (oTareaRepository.existsByNombre(oTareasEntity.getNombre())) {
            throw new ValidationException("el campo username está repetido");
        }
    }

    public TareaEntity get(Long id) {
        //oAuthService.OnlyAdmins();
        return oTareaRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Categorias with id: " + id + " not found"));
    }

    public Long count() {
        //oAuthService.OnlyAdmins();
        return oTareaRepository.count();
    }

    public Long update(TareaEntity oCategoriasEntity) {
        validate(oCategoriasEntity.getId());
        //oAuthService.OnlyAdmins();
        return oTareaRepository.save(oCategoriasEntity).getId();
    }

    public Long create(TareaEntity oNewCategoriasEntity) {
        //oAuthService.OnlyAdmins();
        validate(oNewCategoriasEntity);
        oNewCategoriasEntity.setId(0L);
        return oTareaRepository.save(oNewCategoriasEntity).getId();
    }

    public Long delete(Long id) {
        //oAuthService.OnlyAdmins();
        validate(id);
        oTareaRepository.deleteById(id);
        if (oTareaRepository.existsById(id)) {
            throw new ResourceNotModifiedException("can't remove register " + id);
        } else {
            return id;
        }
    }
    

}

