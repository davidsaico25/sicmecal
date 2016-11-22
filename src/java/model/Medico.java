package model;
// Generated Nov 22, 2016 11:28:14 AM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Medico generated by hbm2java
 */
public class Medico  implements java.io.Serializable {


     private String username;
     private Especialidad especialidad;
     private Usuario usuario;
     private char estado;
     private Set turnos = new HashSet(0);

    public Medico() {
    }

	
    public Medico(Especialidad especialidad, Usuario usuario, char estado) {
        this.especialidad = especialidad;
        this.usuario = usuario;
        this.estado = estado;
    }
    public Medico(Especialidad especialidad, Usuario usuario, char estado, Set turnos) {
       this.especialidad = especialidad;
       this.usuario = usuario;
       this.estado = estado;
       this.turnos = turnos;
    }
   
    public String getUsername() {
        return this.username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    public Especialidad getEspecialidad() {
        return this.especialidad;
    }
    
    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }
    public Usuario getUsuario() {
        return this.usuario;
    }
    
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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


