package net.ausiasmarch.uSmartEnterprise.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PreRemove;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name = "usuario")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class UsuarioEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String apellidos;
    private String email;
    private String empleo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_empresa")
    private EmpresaEntity empresa;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_tipodecuenta")
    private TipodecuentaEntity tipodecuenta;

    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
    private final List<TareaEntity> tareas; 
 
    public UsuarioEntity() {
        this.tareas = new ArrayList<>();
    }

    public UsuarioEntity(Long id) {
        this.tareas = new ArrayList<>();
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

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmpleo() {
        return empleo;
    }

    public void setEmpleo(String empleo) {
        this.empleo = empleo;
    }

    public EmpresaEntity getEmpresa() {
        return empresa;
    }

    public void setEmpresa(EmpresaEntity empresa) {
        this.empresa = empresa;
    }

    public void setTipodecuenta(TipodecuentaEntity tipodeCuenta) {
        this.tipodecuenta = tipodeCuenta;
    }

    public int getTarea() {
        return tareas.size();
    }

    public TipodecuentaEntity getTipodecuenta() {
        return tipodecuenta;
    }

    @PreRemove
    public void nullify() {
        this.tareas.forEach(c -> c.setIdUsuario(null));
    }
}
