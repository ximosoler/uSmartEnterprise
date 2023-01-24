package net.ausiasmarch.uSmartEnterprise.api;

import java.util.List;

import org.springdoc.api.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import net.ausiasmarch.uSmartEnterprise.entity.TipodeCuentaEntity;
import net.ausiasmarch.uSmartEnterprise.service.TipodeCuentaService;

@RestController
@RequestMapping("/tipodeCuenta")
public class TipodeCuentaController {

    @Autowired
    TipodeCuentaService oTipodeCuentaService;

    @GetMapping("/{id}")
    public ResponseEntity<TipodeCuentaEntity> get(@PathVariable(value = "id") Long id) {
        return new ResponseEntity<TipodeCuentaEntity>(oTipodeCuentaService.get(id), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<TipodeCuentaEntity>> all() {
        return new ResponseEntity<List<TipodeCuentaEntity>>(oTipodeCuentaService.all(), HttpStatus.OK);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> count() {
        return new ResponseEntity<Long>(oTipodeCuentaService.count(), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<Page<TipodeCuentaEntity>> getPage(
            @ParameterObject @PageableDefault(page = 0, size = 10, direction = Sort.Direction.DESC) Pageable oPageable,
            @RequestParam(name = "filter", required = false) String strFilter) {
        return new ResponseEntity<Page<TipodeCuentaEntity>>(oTipodeCuentaService.getPage(oPageable, strFilter), HttpStatus.OK);
    }

    @PutMapping("")
    public ResponseEntity<Long> update(@RequestBody TipodeCuentaEntity oTipodeCuentaEntity) {
        return new ResponseEntity<Long>(oTipodeCuentaService.update(oTipodeCuentaEntity), HttpStatus.OK);
    }


}
