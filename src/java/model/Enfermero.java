package model;
// Generated Nov 22, 2016 1:56:10 AM by Hibernate Tools 4.3.1



/**
 * Enfermero generated by hbm2java
 */
public class Enfermero  implements java.io.Serializable {


     private String username;
     private Usuario usuario;
     private char estado;

    public Enfermero() {
    }

    public Enfermero(Usuario usuario, char estado) {
       this.usuario = usuario;
       this.estado = estado;
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
    public char getEstado() {
        return this.estado;
    }
    
    public void setEstado(char estado) {
        this.estado = estado;
    }




}


