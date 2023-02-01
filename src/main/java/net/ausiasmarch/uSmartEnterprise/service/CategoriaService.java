package net.ausiasmarch.uSmartEnterprise.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.ausiasmarch.uSmartEnterprise.entity.CategoriaEntity;

import net.ausiasmarch.uSmartEnterprise.repository.CategoriaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import net.ausiasmarch.uSmartEnterprise.exception.ResourceNotFoundException;
import net.ausiasmarch.uSmartEnterprise.exception.ResourceNotModifiedException;
import net.ausiasmarch.uSmartEnterprise.exception.ValidationException;
import net.ausiasmarch.uSmartEnterprise.exception.CannotPerformOperationException;
import net.ausiasmarch.uSmartEnterprise.helper.RandomHelper;
import net.ausiasmarch.uSmartEnterprise.helper.ValidationHelper;

@Service
public class CategoriaService {

    @Autowired
    CategoriaRepository oCategoriaRepository;

    private final List<String> nombre = List.of("IT","Marketing", "Ventas", "Mantenimiento");

    public void validate(Long id) {
        if (!oCategoriaRepository.existsById(id)) {
            throw new ResourceNotFoundException("id " + id + " not exist");
        }
    }

    public void validate(CategoriaEntity oCategoriasEntity) {
        ValidationHelper.validateStringLength(oCategoriasEntity.getNombre(), 2, 50, "campo nombre de la Categoría(el campo debe tener longitud de 2 a 50 caracteres)");
        if (oCategoriaRepository.existsByNombre(oCategoriasEntity.getNombre())) {
            throw new ValidationException("el campo username está repetido");
        }
    }

    public CategoriaEntity get(Long id) {
        //oAuthService.OnlyAdmins();
        return oCategoriaRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Categorias with id: " + id + " not found"));
    }

    public Long count() {
        //oAuthService.OnlyAdmins();
        return oCategoriaRepository.count();
    }

    public Long update(CategoriaEntity oCategoriasEntity) {
        validate(oCategoriasEntity.getId());
        //oAuthService.OnlyAdmins();
        return oCategoriaRepository.save(oCategoriasEntity).getId();
    }
    public Long create(CategoriaEntity oNewCategoriasEntity) {
        //oAuthService.OnlyAdmins();
         /* validate(oNewCategoriasEntity); */
        oNewCategoriasEntity.setId(0L);
        return oCategoriaRepository.save(oNewCategoriasEntity).getId();
    }

    public Long delete(Long id) {
        //oAuthService.OnlyAdmins();
        validate(id);
        oCategoriaRepository.deleteById(id);
        if (oCategoriaRepository.existsById(id)) {
            throw new ResourceNotModifiedException("can't remove register " + id);
        } else {
            return id;
        }
    }  

    public Page<CategoriaEntity> getPage(Pageable oPageable, String srtFilter, Long lCategorias) {
        /* oAuthService.OnlyAdminsOrUsers(); */
        ValidationHelper.validateRPP(oPageable.getPageSize());
        Page<CategoriaEntity> oPage = null;
        /* if (oAuthService.isAdmin()) { */
            if (lCategorias != null) {
                oPage = oCategoriaRepository.findByNombre(srtFilter, oPageable);
            } else {
                oPage = oCategoriaRepository.findAll(oPageable);
            }
                return oPage;
    
    }

    public CategoriaEntity generateOne() {
        //oAuthService.OnlyAdmins();
        return oCategoriaRepository.save(generateCategorias());
    }

    public CategoriaEntity getOneRandom() {
        if (count() > 0) {
            CategoriaEntity oCategoriasEntity = null;
            int iPosicion = RandomHelper.getRandomInt(0, (int) oCategoriaRepository.count() - 1);
            Pageable oPageable = PageRequest.of(iPosicion, 1);
            Page<CategoriaEntity> CategoriasPage = oCategoriaRepository.findAll(oPageable);
            List<CategoriaEntity> CategoriasList = CategoriasPage.getContent();
            oCategoriasEntity = oCategoriaRepository.getById(CategoriasList.get(0).getId());
            return oCategoriasEntity;
        } else {
            throw new CannotPerformOperationException("ho hay Categorias en la base de datos");
        }
    }

    private CategoriaEntity generateCategorias() {
        CategoriaEntity oCategoriasEntity = new CategoriaEntity();

        oCategoriasEntity.setNombre(nombre.get(RandomHelper.getRandomInt(0, nombre.size() - 1)));
        

        return oCategoriasEntity;
    }

    public CategoriaEntity generate() {
        /* oAuthService.OnlyAdmins(); */
        return generateCategorias();
    }

    public Long generateSome(Integer amount) {
        /* oAuthService.OnlyAdmins(); */
        List<CategoriaEntity> userList = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            CategoriaEntity oUsuarioEntity = generateCategorias();
            oCategoriaRepository.save(oUsuarioEntity);
            userList.add(oUsuarioEntity);
        }
        return oCategoriaRepository.count();
    }

}
        