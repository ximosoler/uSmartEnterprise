package net.ausiasmarch.uSmartEnterprise.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.ausiasmarch.uSmartEnterprise.entity.EmpresaEntity;

@Repository
public interface EmpresaRepository extends JpaRepository<EmpresaEntity, Long> {


    boolean existsByNombre(String nombre);
    Page<EmpresaEntity> findAll(Pageable oPageable);
    Page<EmpresaEntity> findByNombre(String nombre, Pageable oPageable);
    
}
