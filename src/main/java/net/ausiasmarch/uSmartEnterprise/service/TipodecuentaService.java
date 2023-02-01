package net.ausiasmarch.uSmartEnterprise.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import net.ausiasmarch.uSmartEnterprise.entity.TipodecuentaEntity;
import net.ausiasmarch.uSmartEnterprise.exception.ResourceNotFoundException;
import net.ausiasmarch.uSmartEnterprise.helper.ValidationHelper;
import net.ausiasmarch.uSmartEnterprise.repository.TipodecuentaRepository;

@Service
public class TipodecuentaService {

    @Autowired
    TipodecuentaRepository oTipodecuentaRepository;

    public void validate(Long id) {
        if (!oTipodecuentaRepository.existsById(id)) {
            throw new ResourceNotFoundException("id " + id + " not exist");
        }
    }

    public void validate(TipodecuentaEntity oTipodeCuentaEntity) {
        ValidationHelper.validateStringLength(oTipodeCuentaEntity.getNombre(), 2, 100,
                "campo nombre de TipodeCuenta (el campo debe tener longitud de 2 a 100 caracteres)");
    }


    public TipodecuentaEntity get(Long id) {
        validate(id);
        return oTipodecuentaRepository.getById(id);
    }

    public List<TipodecuentaEntity> all() {
        return oTipodecuentaRepository.findAll();
    }

    public Long count() {
        return oTipodecuentaRepository.count();
    }

    public Page<TipodecuentaEntity> getPage(Pageable oPageable, String strFilter) {
        ValidationHelper.validateRPP(oPageable.getPageSize());
        Page<TipodecuentaEntity> oPage = null;
        if (strFilter == null || strFilter.isEmpty() || strFilter.trim().isEmpty()) {
            oPage = oTipodecuentaRepository.findAll(oPageable);
        } else {
            oPage = oTipodecuentaRepository.findByNombreIgnoreCaseContaining(strFilter, oPageable);
        }
        return oPage;
    }

    public Long update(TipodecuentaEntity oTipodeCuentaEntity) {
        /*
         * oAuthService.OnlyAdmins();
         */ validate(oTipodeCuentaEntity.getId());
        validate(oTipodeCuentaEntity);
        return oTipodecuentaRepository.save(oTipodeCuentaEntity).getId();
    }

}
