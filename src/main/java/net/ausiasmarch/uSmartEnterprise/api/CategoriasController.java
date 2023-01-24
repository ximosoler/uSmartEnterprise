package net.ausiasmarch.uSmartEnterprise.api;

import org.springdoc.api.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import net.ausiasmarch.uSmartEnterprise.entity.CategoriasEntity;
import net.ausiasmarch.uSmartEnterprise.service.CategoriasService;

@RestController
@RequestMapping("/categorias")

public class CategoriasController {

    @Autowired
    CategoriasService oCategoriasService;

    @GetMapping("/{id}")
    public ResponseEntity<CategoriasEntity> get(@PathVariable(value="id") Long id) {
        return new ResponseEntity<CategoriasEntity>(oCategoriasService.get(id), HttpStatus.OK);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> count(){
        return new ResponseEntity<Long>(oCategoriasService.count(), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<Page<CategoriasEntity>> getPage(
            @ParameterObject @PageableDefault(page = 0, size = 10, direction = Sort.Direction.DESC) Pageable oPageable,
            @RequestParam(name = "filter", required = false) String strFilter,
            @RequestParam(name = "categorias", required = false) Long lCategorias) {
        return new ResponseEntity<Page<CategoriasEntity>>(oCategoriasService.getPage(oPageable, strFilter, lCategorias), HttpStatus.OK);
    } 


    @PutMapping("/{id}")
    public ResponseEntity<Long> update(@RequestBody CategoriasEntity oCategoriasEntity) {
        return new ResponseEntity<Long>(oCategoriasService.update(oCategoriasEntity), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Long> create(@RequestBody CategoriasEntity oNewCategoriasEntity){
        return new ResponseEntity<Long>(oCategoriasService.create(oNewCategoriasEntity), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable(value = "id") Long id){
        return new ResponseEntity<Long>(oCategoriasService.delete(id), HttpStatus.OK);
    }

    @PostMapping("/generate")
    public ResponseEntity<CategoriasEntity> generate() {
        return new ResponseEntity<CategoriasEntity>(oCategoriasService.generate(), HttpStatus.OK);
    }
    @PostMapping("/generate/{amount}")
    public ResponseEntity<Long> generateSome(@PathVariable(value = "amount") Integer amount) {
        return new ResponseEntity<>(oCategoriasService.generateSome(amount), HttpStatus.OK);
    }
}
