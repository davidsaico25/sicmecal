package model;
// Generated Nov 22, 2016 11:28:14 AM by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * Especialidad generated by hbm2java
 */
public class Especialidad  implements java.io.Serializable {


     private BigDecimal codEspecialidad;
     private String nombre;
     private BigDecimal precioConsulta;
     private char estado;
     private Set medicos = new HashSet(0);

    public Especialidad() {
    }

	
    public Especialidad(BigDecimal codEspecialidad, String nombre, BigDecimal precioConsulta, char estado) {
        this.codEspecialidad = codEspecialidad;
        this.nombre = nombre;
        this.precioConsulta = precioConsulta;
        this.estado = estado;
    }
    public Especialidad(BigDecimal codEspecialidad, String nombre, BigDecimal precioConsulta, char estado, Set medicos) {
       this.codEspecialidad = codEspecialidad;
       this.nombre = nombre;
       this.precioConsulta = precioConsulta;
       this.estado = estado;
       this.medicos = medicos;
    }
   
    public BigDecimal getCodEspecialidad() {
        return this.codEspecialidad;
    }
    
    public void setCodEspecialidad(BigDecimal codEspecialidad) {
        this.codEspecialidad = codEspecialidad;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public BigDecimal getPrecioConsulta() {
        return this.precioConsulta;
    }
    
    public void setPrecioConsulta(BigDecimal precioConsulta) {
        this.precioConsulta = precioConsulta;
    }
    public char getEstado() {
        return this.estado;
    }
    
    public void setEstado(char estado) {
        this.estado = estado;
    }
    public Set getMedicos() {
        return this.medicos;
    }
    
    public void setMedicos(Set medicos) {
        this.medicos = medicos;
    }




}


