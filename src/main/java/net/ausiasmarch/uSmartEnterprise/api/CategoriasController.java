package net.ausiasmarch.uSmartEnterprise.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import net.ausiasmarch.uSmartEnterprise.entity.CategoriasEntity;
import net.ausiasmarch.uSmartEnterprise.service.CategoriasService;

@RestController
@RequestMapping("/categorias")
public class CategoriasController {
    
    private final CategoriasService oCategoriasService;

    @Autowired
    public CategoriasController(CategoriasService oCategoriasService) {
            this.oCategoriasService = oCategoriasService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriasEntity> get(@PathVariable(value="id")Long id) {
        return new ResponseEntity<>(oCategoriasService.get(id), HttpStatus.OK);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> count(){
        return new ResponseEntity<Long>(oCategoriasService.count(), HttpStatus.OK);
    }

    @PutMapping("")
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

}
