package net.ausiasmarch.uSmartEnterprise.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.ausiasmarch.uSmartEnterprise.entity.EmpresaEntity;

public interface EmpresaRepository extends JpaRepository<EmpresaEntity, Long> {


    boolean existsByNombre(String nombre);
    
}
