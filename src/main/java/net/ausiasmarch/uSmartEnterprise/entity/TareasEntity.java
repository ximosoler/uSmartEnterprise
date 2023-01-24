package net.ausiasmarch.uSmartEnterprise.entity;

import java.io.Serializable;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tareas")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class TareasEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String descripcion;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idCategoria")
    private CategoriasEntity categorias;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idUsuario")
    private UsuariosEntity usuarios;
  

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UsuariosEntity getIdUsuario() {
        return usuarios;
    }

    public void setIdUsuario(UsuariosEntity usuarios) {
        this.usuarios = usuarios;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public CategoriasEntity getCategorias() {
        return categorias;
    }

    public void setCategorias(CategoriasEntity categorias) {
        this.categorias = categorias;
    }

}
