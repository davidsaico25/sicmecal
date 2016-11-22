package model;
// Generated Nov 22, 2016 11:28:14 AM by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * Perfil generated by hbm2java
 */
public class Perfil  implements java.io.Serializable {


     private BigDecimal codPerfil;
     private String nombre;
     private char estado;
     private Set usuarios = new HashSet(0);
     private Set modulos = new HashSet(0);

    public Perfil() {
    }

	
    public Perfil(BigDecimal codPerfil, String nombre, char estado) {
        this.codPerfil = codPerfil;
        this.nombre = nombre;
        this.estado = estado;
    }
    public Perfil(BigDecimal codPerfil, String nombre, char estado, Set usuarios, Set modulos) {
       this.codPerfil = codPerfil;
       this.nombre = nombre;
       this.estado = estado;
       this.usuarios = usuarios;
       this.modulos = modulos;
    }
   
    public BigDecimal getCodPerfil() {
        return this.codPerfil;
    }
    
    public void setCodPerfil(BigDecimal codPerfil) {
        this.codPerfil = codPerfil;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public char getEstado() {
        return this.estado;
    }
    
    public void setEstado(char estado) {
        this.estado = estado;
    }
    public Set getUsuarios() {
        return this.usuarios;
    }
    
    public void setUsuarios(Set usuarios) {
        this.usuarios = usuarios;
    }
    public Set getModulos() {
        return this.modulos;
    }
    
    public void setModulos(Set modulos) {
        this.modulos = modulos;
    }




}


