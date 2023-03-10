 package net.ausiasmarch.uSmartEnterprise.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "empresa")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class EmpresaEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @OneToMany(mappedBy = "empresa", fetch = FetchType.LAZY)
    private final List<UsuarioEntity> usuario;

    
    public EmpresaEntity() {
        this.usuario = new ArrayList<>();
    }

    public EmpresaEntity(Long id) {
        this.usuario = new ArrayList<>();
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
 