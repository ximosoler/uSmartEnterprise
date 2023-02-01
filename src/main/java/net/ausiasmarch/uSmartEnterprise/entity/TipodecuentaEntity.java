package net.ausiasmarch.uSmartEnterprise.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PreRemove;
import javax.persistence.Table;

@Entity
@Table(name = "tipodecuenta")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class TipodecuentaEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;

    @OneToMany(mappedBy = "tipodecuenta", fetch = FetchType.LAZY)
    private final List<UsuarioEntity> usuarios;

    public TipodecuentaEntity() {
        this.usuarios = new ArrayList<>();
    }

    public TipodecuentaEntity(Long id) {
        this.usuarios = new ArrayList<>();
        this.id = id;        
    }

    public TipodecuentaEntity(Long id, String nombre) {
        this.usuarios = new ArrayList<>();
        this.id = id;
        this.nombre = nombre;
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

    public int getUsuarios() {
        return usuarios.size();
    }

    @PreRemove
    public void nullify() {
        this.usuarios.forEach(c -> c.setTipodecuenta(null));
    }

}
