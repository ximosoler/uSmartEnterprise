package net.ausiasmarch.uSmartEnterprise.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.ausiasmarch.uSmartEnterprise.entity.CategoriaEntity;
@Repository
public interface CategoriaRepository extends JpaRepository<CategoriaEntity, Long> {

    boolean existsByNombre(String nombre);
    Page<CategoriaEntity> findAll(Pageable oPageable);
    Page<CategoriaEntity> findByNombre(String nombre, Pageable oPageable);
  
}
