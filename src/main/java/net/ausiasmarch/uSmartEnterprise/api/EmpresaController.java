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
import net.ausiasmarch.uSmartEnterprise.entity.EmpresaEntity;
import net.ausiasmarch.uSmartEnterprise.service.EmpresaService;

@RestController
@RequestMapping("/empresa")
public class EmpresaController {
    
    @Autowired
   EmpresaService oEmpresaService;

    @GetMapping("/{id}")
    public ResponseEntity<EmpresaEntity> get(@PathVariable(value="id")Long id) {
        return new ResponseEntity<EmpresaEntity>(oEmpresaService.get(id), HttpStatus.OK);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> count(){
        return new ResponseEntity<Long>(oEmpresaService.count(), HttpStatus.OK);
    }

    @PutMapping("")
    public ResponseEntity<Long> update(@RequestBody EmpresaEntity oCategoriasEntity) {
        return new ResponseEntity<Long>(oEmpresaService.update(oCategoriasEntity), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Long> create(@RequestBody EmpresaEntity oNewCategoriasEntity){
        return new ResponseEntity<Long>(oEmpresaService.create(oNewCategoriasEntity), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable(value = "id") Long id){
        return new ResponseEntity<Long>(oEmpresaService.delete(id), HttpStatus.OK);
    }

}
