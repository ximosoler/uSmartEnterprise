package net.ausiasmarch.uSmartEnterprise.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.ausiasmarch.uSmartEnterprise.entity.TareasEntity;

public interface TareasRepository extends JpaRepository<TareasEntity, Long> {

    boolean existsByNombre(String nombre);

    
}
