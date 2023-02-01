package net.ausiasmarch.uSmartEnterprise.service;

import net.ausiasmarch.uSmartEnterprise.entity.EmpresaEntity;
import net.ausiasmarch.uSmartEnterprise.exception.ResourceNotFoundException;
import net.ausiasmarch.uSmartEnterprise.exception.ResourceNotModifiedException;
import net.ausiasmarch.uSmartEnterprise.helper.ValidationHelper;
import net.ausiasmarch.uSmartEnterprise.repository.EmpresaRepository;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class EmpresaService {

    @Autowired
    EmpresaRepository oEmpresaRepository;
   

    public void validate(Long id) {
        if (!oEmpresaRepository.existsById(id)) {
            throw new ResourceNotFoundException("id " + id + " not exist");
        }
    }

    public void validate(EmpresaEntity oEmpresaEntity) {
        ValidationHelper.validateStringLength(oEmpresaEntity.getNombre(), 2, 50, "campo nombre de la Categoría(el campo debe tener longitud de 2 a 50 caracteres)");
        if (oEmpresaRepository.existsByNombre(oEmpresaEntity.getNombre())) {
            throw new ValidationException("el campo username está repetido");
        }
    }

    public EmpresaEntity get(Long id) {
        //oAuthService.OnlyAdmins();
        return oEmpresaRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Categorias with id: " + id + " not found"));
    }

    public Long count() {
        //oAuthService.OnlyAdmins();
        return oEmpresaRepository.count();
    }

    public Long update(EmpresaEntity oEmpresaEntity) {
        validate(oEmpresaEntity.getId());
        //oAuthService.OnlyAdmins();
        return oEmpresaRepository.save(oEmpresaEntity).getId();
    }

    public Long create(EmpresaEntity oNewEmpresaEntity) {
        //oAuthService.OnlyAdmins();
        validate(oNewEmpresaEntity);
        oNewEmpresaEntity.setId(0L);
        return oEmpresaRepository.save(oNewEmpresaEntity).getId();
    }

    public Long delete(Long id) {
        //oAuthService.OnlyAdmins();
        validate(id);
        oEmpresaRepository.deleteById(id);
        if (oEmpresaRepository.existsById(id)) {
            throw new ResourceNotModifiedException("can't remove register " + id);
        } else {
            return id;
        }
    }  
}
