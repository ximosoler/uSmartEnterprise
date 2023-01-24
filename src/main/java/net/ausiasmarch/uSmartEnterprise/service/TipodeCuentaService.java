package net.ausiasmarch.uSmartEnterprise.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import net.ausiasmarch.uSmartEnterprise.entity.TipodeCuentaEntity;
import net.ausiasmarch.uSmartEnterprise.exception.ResourceNotFoundException;
import net.ausiasmarch.uSmartEnterprise.helper.ValidationHelper;
import net.ausiasmarch.uSmartEnterprise.repository.TipodeCuentaRepository;

@Service
public class TipodeCuentaService {

    @Autowired
    TipodeCuentaRepository oTipodeCuentaRepository;

    public void validate(Long id) {
        if (!oTipodeCuentaRepository.existsById(id)) {
            throw new ResourceNotFoundException("id " + id + " not exist");
        }
    }

    public void validate(TipodeCuentaEntity oTipodeCuentaEntity) {
        ValidationHelper.validateStringLength(oTipodeCuentaEntity.getNombre(), 2, 100,
                "campo nombre de TipodeCuenta (el campo debe tener longitud de 2 a 100 caracteres)");
    }


    public TipodeCuentaEntity get(Long id) {
        validate(id);
        return oTipodeCuentaRepository.getById(id);
    }

    public List<TipodeCuentaEntity> all() {
        return oTipodeCuentaRepository.findAll();
    }

    public Long count() {
        return oTipodeCuentaRepository.count();
    }

    public Page<TipodeCuentaEntity> getPage(Pageable oPageable, String strFilter) {
        ValidationHelper.validateRPP(oPageable.getPageSize());
        Page<TipodeCuentaEntity> oPage = null;
        if (strFilter == null || strFilter.isEmpty() || strFilter.trim().isEmpty()) {
            oPage = oTipodeCuentaRepository.findAll(oPageable);
        } else {
            oPage = oTipodeCuentaRepository.findByNombreIgnoreCaseContaining(strFilter, oPageable);
        }
        return oPage;
    }

    public Long update(TipodeCuentaEntity oTipodeCuentaEntity) {
        /*
         * oAuthService.OnlyAdmins();
         */ validate(oTipodeCuentaEntity.getId());
        validate(oTipodeCuentaEntity);
        return oTipodeCuentaRepository.save(oTipodeCuentaEntity).getId();
    }

}
