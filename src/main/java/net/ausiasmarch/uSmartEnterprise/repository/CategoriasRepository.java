package net.ausiasmarch.uSmartEnterprise.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.ausiasmarch.uSmartEnterprise.entity.CategoriasEntity;

public interface CategoriasRepository extends JpaRepository<CategoriasEntity, Long> {

    boolean existsByNombre(String nombre);

    
}
