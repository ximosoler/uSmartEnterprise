package net.ausiasmarch.uSmartEnterprise.api;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.web.bind.annotation.RequestParam;

import net.ausiasmarch.uSmartEnterprise.entity.UsuariosEntity;
import net.ausiasmarch.uSmartEnterprise.service.UsuariosService;

@RestController
@RequestMapping("/usuarios")
public class UsuariosController {

    private final UsuariosService oUsuariosService;
    

    public UsuariosController(UsuariosService oUsuariosService) {
        this.oUsuariosService = oUsuariosService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuariosEntity> get(@PathVariable(value="id") Long id) {
        return new ResponseEntity<UsuariosEntity>(oUsuariosService.get(id), HttpStatus.OK);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> count() {
        return new ResponseEntity<Long>(oUsuariosService.count(), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<UsuariosEntity>> getPage(
            @ParameterObject @PageableDefault(page = 0, size = 10, direction = Sort.Direction.DESC) Pageable oPageable,
            @RequestParam(name = "filter", required = false) String strFilter,
            @RequestParam(value = "empresa", required = false) Long idEmpresa,
            @RequestParam(value = "tipodecuenta", required = false) Long idTipodeCuenta) {
        return new ResponseEntity<>(oUsuariosService.getPage(oPageable, strFilter, idEmpresa, idTipodeCuenta), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Long> update(@RequestBody UsuariosEntity oUsuariosEntity) {
        return new ResponseEntity<Long>(oUsuariosService.update(oUsuariosEntity), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Long> create(@RequestBody UsuariosEntity oNewUsuariosEntity) {
        return new ResponseEntity<Long>(oUsuariosService.create(oNewUsuariosEntity), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable(value = "id") Long id) {
        return new ResponseEntity<Long>(oUsuariosService.delete(id), HttpStatus.OK);
    }

    @PostMapping("/generate")
    public ResponseEntity<UsuariosEntity> generate() {
        return new ResponseEntity<UsuariosEntity>(oUsuariosService.generate(), HttpStatus.OK);
    }
    @PostMapping("/generate/{amount}")
    public ResponseEntity<Long> generateSome(@PathVariable(value = "amount") Integer amount) {
        return new ResponseEntity<>(oUsuariosService.generateSome(amount), HttpStatus.OK);
    }
    
}
