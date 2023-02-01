package net.ausiasmarch.uSmartEnterprise.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.ausiasmarch.uSmartEnterprise.entity.UsuarioEntity;

@Repository
public interface UsuarioRepository extends JpaRepository <UsuarioEntity, Long> {

    Page<UsuarioEntity> findAll(Pageable oPageable);
    Page<UsuarioEntity> findByNombre(String nombre, Pageable oPageable);
    
    Page<UsuarioEntity> findByIdtiposdecuenta(Long idtiposdecuenta, Pageable oPageable);
    Page<UsuarioEntity> findByIdempresa(Long Empresa, Pageable oPageable);
    Page<UsuarioEntity> findByIdempresaAndIdtiposdecuenta(Long Empresa ,Long idtiposdecuenta, Pageable oPageable);
    Page<UsuarioEntity> findByNombreIgnoreCaseContainingOrApellidosIgnoreCaseContaining(String nombre, String apellidos, Pageable oPageable);
    Page<UsuarioEntity> findByNombreIgnoreCaseContainingOrApellidosIgnoreCaseContainingAndIdtiposdecuenta(String nombre, String apellidos, Long idtiposdecuenta, Pageable oPageable);
    Page<UsuarioEntity> findByNombreIgnoreCaseContainingOrApellidosIgnoreCaseContainingAndIdempresa(String nombre, String apellidos, Long empresa, Pageable oPageable);
    Page<UsuarioEntity> findByNombreIgnoreCaseContainingOrApellidosIgnoreCaseContainingAndIdempresaAndIdtiposdecuenta(String nombre, String apellidos,  Long idtiposdecuenta,Long Empresa,Pageable oPageable);
  
}
