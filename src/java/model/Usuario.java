package model;
// Generated Nov 22, 2016 11:28:14 AM by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Usuario generated by hbm2java
 */
public class Usuario  implements java.io.Serializable {


     private String username;
     private Perfil perfil;
     private Persona persona;
     private String password;
     private char estado;
     private Date fechaModificacion;
     private Date fechaCreacion;
     private Cajero cajero;
     private Set recepcionistas = new HashSet(0);
     private Medico medico;
     private Paciente paciente;
     private Tecnologo tecnologo;
     private Enfermero enfermero;

    public Usuario() {
    }

	
    public Usuario(String username, Perfil perfil, Persona persona, String password, char estado, Date fechaModificacion, Date fechaCreacion) {
        this.username = username;
        this.perfil = perfil;
        this.persona = persona;
        this.password = password;
        this.estado = estado;
        this.fechaModificacion = fechaModificacion;
        this.fechaCreacion = fechaCreacion;
    }
    public Usuario(String username, Perfil perfil, Persona persona, String password, char estado, Date fechaModificacion, Date fechaCreacion, Cajero cajero, Set recepcionistas, Medico medico, Paciente paciente, Tecnologo tecnologo, Enfermero enfermero) {
       this.username = username;
       this.perfil = perfil;
       this.persona = persona;
       this.password = password;
       this.estado = estado;
       this.fechaModificacion = fechaModificacion;
       this.fechaCreacion = fechaCreacion;
       this.cajero = cajero;
       this.recepcionistas = recepcionistas;
       this.medico = medico;
       this.paciente = paciente;
       this.tecnologo = tecnologo;
       this.enfermero = enfermero;
    }
   
    public String getUsername() {
        return this.username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    public Perfil getPerfil() {
        return this.perfil;
    }
    
    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }
    public Persona getPersona() {
        return this.persona;
    }
    
    public void setPersona(Persona persona) {
        this.persona = persona;
    }
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    public char getEstado() {
        return this.estado;
    }
    
    public void setEstado(char estado) {
        this.estado = estado;
    }
    public Date getFechaModificacion() {
        return this.fechaModificacion;
    }
    
    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }
    public Date getFechaCreacion() {
        return this.fechaCreacion;
    }
    
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
    public Cajero getCajero() {
        return this.cajero;
    }
    
    public void setCajero(Cajero cajero) {
        this.cajero = cajero;
    }
    public Set getRecepcionistas() {
        return this.recepcionistas;
    }
    
    public void setRecepcionistas(Set recepcionistas) {
        this.recepcionistas = recepcionistas;
    }
    public Medico getMedico() {
        return this.medico;
    }
    
    public void setMedico(Medico medico) {
        this.medico = medico;
    }
    public Paciente getPaciente() {
        return this.paciente;
    }
    
    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
    public Tecnologo getTecnologo() {
        return this.tecnologo;
    }
    
    public void setTecnologo(Tecnologo tecnologo) {
        this.tecnologo = tecnologo;
    }
    public Enfermero getEnfermero() {
        return this.enfermero;
    }
    
    public void setEnfermero(Enfermero enfermero) {
        this.enfermero = enfermero;
    }




}


