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
import javax.persistence.Table;



@Entity
@Table(name = "tipodecuenta")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class TipodeCuentaEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;

  @OneToMany(mappedBy = "tipodecuenta", fetch = FetchType.LAZY)
    private final List<UsuariosEntity> usuarios;


    public TipodeCuentaEntity() {
        this.usuarios = new ArrayList<>();
    }

    public TipodeCuentaEntity(Long id) {
        this.usuarios = new ArrayList<>();
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

    public int getUsuarios() {
        return usuarios.size();
    }

    

}
