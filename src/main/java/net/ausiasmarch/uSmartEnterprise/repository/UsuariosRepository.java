package net.ausiasmarch.uSmartEnterprise.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import net.ausiasmarch.uSmartEnterprise.entity.UsuariosEntity;

public interface UsuariosRepository extends JpaRepository <UsuariosEntity, Long> {

    Page<UsuariosEntity> findByNombre(Long lUsuarios, Pageable oPageable);
    Page<UsuariosEntity> findByTipodeCuenta(Long lTipodeCuenta, Pageable oPageable);
    Page<UsuariosEntity> findByEmpresa(Long lEmpresa, Pageable oPageable);
    Page<UsuariosEntity> findByEmpresaAndTipodeCuenta(Long lEmpresaLong ,Long lTipodeCuenta, Pageable oPageable);
    Page<UsuariosEntity> findByNombreIgnoreCaseContainingOrApellidosIgnoreCaseContaining(String strFilter,String strFilter2, Pageable oPageable);
    Page<UsuariosEntity> findByNombreIgnoreCaseContainingOrApellidosIgnoreCaseContainingAndTipodeCuenta(String strFilter, String strFilter2, String strFilter3, Long idTipodeCuenta, Pageable oPageable);
    Page<UsuariosEntity> findByNombreIgnoreCaseContainingOrApellidosIgnoreCaseContainingaAndEmpresa(String strFilter,String strFilter2, String strFilter3, Long idEmpresa, Pageable oPageable);
    Page<UsuariosEntity> findByNombreIgnoreCaseContainingOrApellidosIgnoreCaseContainingaAndEmpresaAndTipodeCuenta(String strFilter, String strFilter2, String strFilter3, Long idEmpresa, Long idTipodeCuenta,Pageable oPageable);
}
