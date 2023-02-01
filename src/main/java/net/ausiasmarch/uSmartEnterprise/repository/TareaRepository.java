package net.ausiasmarch.uSmartEnterprise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.ausiasmarch.uSmartEnterprise.entity.TareaEntity;

@Repository
public interface TareaRepository extends JpaRepository<TareaEntity, Long> {

    boolean existsByNombre(String nombre);

    
}
