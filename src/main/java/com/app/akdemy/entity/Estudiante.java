package com.app.akdemy.entity;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.query.criteria.internal.path.ListAttributeJoin;

@Entity
public class Estudiante {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    @Column(name = "est_nombres", nullable = false, length = 70) 
	private String nombres;

    @Column(name = "est_apellidos", nullable = false, length = 70) 
	private String apellidos;

    @Column(name = "est_fecha_nacimiento")
    private Date fechaNacimiento;

    @ManyToOne
    @JoinColumn(name = "est_tipo_doc")
    private TipoDocumento tipoDocumento;

    @Column(name = "est_documento")
	private String documento;

    //Relaciones con otras tablas

    @ManyToOne
    @JoinColumn(name = "est_eps")
    private Eps eps;

    @ManyToOne
    @JoinColumn(name = "est_gsrh")
    private GrupoSanguineoRH grupoSanguineoRH;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "est_usuario", nullable = false)
    private User usuario;

    @ManyToMany
    @JoinTable(name = "acudiente_estudiante"
            , joinColumns = @JoinColumn(name = "id_estudiante")
            , inverseJoinColumns = @JoinColumn(name = "id_acudiente"))
    private Set<Acudiente> acudientes;

    @ManyToMany(mappedBy = "estudiantes")
    private List<Curso> cursos;

    //Constructor

    public Estudiante() {
    }

    public Estudiante(long id, String nombres, String apellidos, Date fechaNacimiento, TipoDocumento tipoDocumento, String documento, Eps eps, GrupoSanguineoRH grupoSanguineoRH, User usuario, Set<Acudiente> acudientes, List<Curso> cursos) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
        this.tipoDocumento = tipoDocumento;
        this.documento = documento;
        this.eps = eps;
        this.grupoSanguineoRH = grupoSanguineoRH;
        this.usuario = usuario;
        this.acudientes = acudientes;
        this.cursos = cursos;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombres() {
        return this.nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return this.apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Date getFechaNacimiento() {
        return this.fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public TipoDocumento getTipoDocumento() {
        return this.tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getDocumento() {
        return this.documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public Eps getEps() {
        return this.eps;
    }

    public void setEps(Eps eps) {
        this.eps = eps;
    }

    public GrupoSanguineoRH getGrupoSanguineoRH() {
        return this.grupoSanguineoRH;
    }

    public void setGrupoSanguineoRH(GrupoSanguineoRH grupoSanguineoRH) {
        this.grupoSanguineoRH = grupoSanguineoRH;
    }

    public User getUsuario() {
        return this.usuario;
    }

    public void setUsuario(User usuario) {
        this.usuario = usuario;
    }

    public Set<Acudiente> getAcudientes() {
        return this.acudientes;
    }

    public void setAcudientes(Set<Acudiente> acudientes) {
        this.acudientes = acudientes;
    }

    public List<Curso> getCursos() {
        return this.cursos;
    }

    public void setCursos(List<Curso> cursos) {
        this.cursos = cursos;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Estudiante)) {
            return false;
        }
        Estudiante estudiante = (Estudiante) o;
        return id == estudiante.id && Objects.equals(nombres, estudiante.nombres) && Objects.equals(apellidos, estudiante.apellidos) && Objects.equals(fechaNacimiento, estudiante.fechaNacimiento) && Objects.equals(tipoDocumento, estudiante.tipoDocumento) && Objects.equals(documento, estudiante.documento) && Objects.equals(eps, estudiante.eps) && Objects.equals(grupoSanguineoRH, estudiante.grupoSanguineoRH) && Objects.equals(usuario, estudiante.usuario) && Objects.equals(acudientes, estudiante.acudientes) && Objects.equals(cursos, estudiante.cursos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombres, apellidos, fechaNacimiento, tipoDocumento, documento, eps, grupoSanguineoRH, usuario, acudientes, cursos);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", nombres='" + getNombres() + "'" +
            ", apellidos='" + getApellidos() + "'" +
            ", fechaNacimiento='" + getFechaNacimiento() + "'" +
            ", tipoDocumento='" + getTipoDocumento() + "'" +
            ", documento='" + getDocumento() + "'" +
            ", eps='" + getEps() + "'" +
            ", grupoSanguineoRH='" + getGrupoSanguineoRH() + "'" +
            ", usuario='" + getUsuario() + "'" +
            ", acudientes='" + getAcudientes() + "'" +
            ", cursos='" + getCursos() + "'" +
            "}";
    }

    public Curso getCursoActual(){
        List<Curso> cursos= this.cursos;
        if(cursos == null || cursos.isEmpty())
            return null;
        
        Integer anioActual = LocalDate.now().getYear();
        for(Curso curso: cursos){
            if(curso.getAnio_Curso().equals(anioActual))
                return curso;
        }

        return null;
    }
    
}
