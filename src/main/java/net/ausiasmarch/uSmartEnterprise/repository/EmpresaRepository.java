package net.ausiasmarch.uSmartEnterprise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.ausiasmarch.uSmartEnterprise.entity.EmpresaEntity;

@Repository
public interface EmpresaRepository extends JpaRepository<EmpresaEntity, Long> {


    boolean existsByNombre(String nombre);
    
}
