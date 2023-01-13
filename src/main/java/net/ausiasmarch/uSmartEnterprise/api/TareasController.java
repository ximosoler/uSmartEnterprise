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
import net.ausiasmarch.uSmartEnterprise.entity.TareasEntity;
import net.ausiasmarch.uSmartEnterprise.service.TareasService;

@RestController
@RequestMapping("/task")
public class TareasController {
    
    @Autowired
    TareasService oTareasService;

    @GetMapping("/{id}")
    public ResponseEntity<TareasEntity> get(@PathVariable(value = "id") Long id){
        return new ResponseEntity<TareasEntity>(oTareasService.get(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public Long delete(@PathVariable(value = "id") Long id){
        return oTareasService.delete(id);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> count(){
        return new ResponseEntity<Long>(oTareasService.count(), HttpStatus.OK);
    }
  /*   @GetMapping("")
    public ResponseEntity<Page<TareasEntity>> getPage(
            @ParameterObject @PageableDefault(page = 0, size = 10, direction = Sort.Direction.DESC) Pageable oPageable,
            @RequestParam(name = "filter", required = false) String strFilter,
            @RequestParam(name = "project", required = false) Long lProject) {
        return new ResponseEntity<Page<CategoriasEntity>>(oCategoriasService.getPage(oPageable, strFilter, lProject), HttpStatus.OK);
    } */

    @PutMapping
    public ResponseEntity<Long> update(@RequestBody TareasEntity oTareasEntity){
        return new ResponseEntity<>(oTareasService.update(oTareasEntity), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Long> create(@RequestBody TareasEntity oNewTareasEntity){
        return new ResponseEntity<Long>(oTareasService.create(oNewTareasEntity), HttpStatus.OK);
    }
}
