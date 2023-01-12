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
@Table(name = "categorias")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class CategoriasEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @OneToMany(mappedBy = "categorias", fetch = FetchType.LAZY)
    private final List<TareasEntity> tarea;

    public CategoriasEntity() {
        this.tarea = new ArrayList<>();
    }

    public CategoriasEntity(Long id) {
        this.tarea = new ArrayList<>();
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

    public int getTareas() {
        return tarea.size();
    }

}
