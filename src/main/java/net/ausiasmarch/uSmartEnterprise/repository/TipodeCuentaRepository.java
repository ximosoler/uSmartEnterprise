package net.ausiasmarch.uSmartEnterprise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import net.ausiasmarch.uSmartEnterprise.entity.TipodeCuentaEntity;

public interface TipodeCuentaRepository extends JpaRepository<TipodeCuentaEntity, Long> {

    boolean existsByNombre(String nombre);
    public Page<TipodeCuentaEntity> findByNombreIgnoreCaseContaining(String strFilter, Pageable oPageable);

    
}