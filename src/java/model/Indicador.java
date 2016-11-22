package model;
// Generated Nov 22, 2016 11:28:14 AM by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * Indicador generated by hbm2java
 */
public class Indicador  implements java.io.Serializable {


     private BigDecimal codIndicador;
     private ExamenLaboratorio examenLaboratorio;
     private String nombre;
     private String descripcion;
     private BigDecimal precio;
     private char estado;
     private Set muestras = new HashSet(0);

    public Indicador() {
    }

	
    public Indicador(BigDecimal codIndicador, ExamenLaboratorio examenLaboratorio, String nombre, String descripcion, BigDecimal precio, char estado) {
        this.codIndicador = codIndicador;
        this.examenLaboratorio = examenLaboratorio;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.estado = estado;
    }
    public Indicador(BigDecimal codIndicador, ExamenLaboratorio examenLaboratorio, String nombre, String descripcion, BigDecimal precio, char estado, Set muestras) {
       this.codIndicador = codIndicador;
       this.examenLaboratorio = examenLaboratorio;
       this.nombre = nombre;
       this.descripcion = descripcion;
       this.precio = precio;
       this.estado = estado;
       this.muestras = muestras;
    }
   
    public BigDecimal getCodIndicador() {
        return this.codIndicador;
    }
    
    public void setCodIndicador(BigDecimal codIndicador) {
        this.codIndicador = codIndicador;
    }
    public ExamenLaboratorio getExamenLaboratorio() {
        return this.examenLaboratorio;
    }
    
    public void setExamenLaboratorio(ExamenLaboratorio examenLaboratorio) {
        this.examenLaboratorio = examenLaboratorio;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getDescripcion() {
        return this.descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public BigDecimal getPrecio() {
        return this.precio;
    }
    
    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }
    public char getEstado() {
        return this.estado;
    }
    
    public void setEstado(char estado) {
        this.estado = estado;
    }
    public Set getMuestras() {
        return this.muestras;
    }
    
    public void setMuestras(Set muestras) {
        this.muestras = muestras;
    }




}


