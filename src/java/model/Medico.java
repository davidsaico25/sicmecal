package model;
// Generated Oct 30, 2016 1:24:18 AM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Medico generated by hbm2java
 */
public class Medico  implements java.io.Serializable {


     private String username;
     private Usuario usuario;
     private Especialidad especialidad;
     private char estado;
     private Set turnos = new HashSet(0);

    public Medico() {
    }

	
    public Medico(Usuario usuario, Especialidad especialidad, char estado) {
        this.usuario = usuario;
        this.especialidad = especialidad;
        this.estado = estado;
    }
    public Medico(Usuario usuario, Especialidad especialidad, char estado, Set turnos) {
       this.usuario = usuario;
       this.especialidad = especialidad;
       this.estado = estado;
       this.turnos = turnos;
    }
   
    public String getUsername() {
        return this.username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    public Usuario getUsuario() {
        return this.usuario;
    }
    
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public Especialidad getEspecialidad() {
        return this.especialidad;
    }
    
    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }
    public char getEstado() {
        return this.estado;
    }
    
    public void setEstado(char estado) {
        this.estado = estado;
    }
    public Set getTurnos() {
        return this.turnos;
    }
    
    public void setTurnos(Set turnos) {
        this.turnos = turnos;
    }




}


