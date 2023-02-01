package net.ausiasmarch.uSmartEnterprise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import net.ausiasmarch.uSmartEnterprise.entity.TipodecuentaEntity;

@Repository
public interface TipodecuentaRepository extends JpaRepository<TipodecuentaEntity, Long> {

    boolean existsByNombre(String nombre);
    public Page<TipodecuentaEntity> findByNombreIgnoreCaseContaining(String nombre, Pageable oPageable);

    
}