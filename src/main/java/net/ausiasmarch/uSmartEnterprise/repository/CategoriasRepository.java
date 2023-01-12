package net.ausiasmarch.uSmartEnterprise.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import net.ausiasmarch.uSmartEnterprise.entity.CategoriasEntity;

public interface CategoriasRepository extends JpaRepository<CategoriasEntity, Long> {

    boolean existsByNombre(String nombre);
    Page<CategoriasEntity> findAll(Pageable oPageable);
    Page<CategoriasEntity> findByCategoriasId (Long lCategorias, Pageable oPageable);
    Page<CategoriasEntity> findByCategoriasIdAndNombreContainingIgnoreCase (Long lCategorias, String strFilter, Pageable oPageable);
    Page<CategoriasEntity> findByNombreContainingIgnoreCase(String strFilter, Pageable oPageable);

   
 
    
}
