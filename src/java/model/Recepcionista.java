package model;
// Generated Nov 22, 2016 1:56:10 AM by Hibernate Tools 4.3.1



/**
 * Recepcionista generated by hbm2java
 */
public class Recepcionista  implements java.io.Serializable {


     private RecepcionistaId id;
     private Usuario usuario;

    public Recepcionista() {
    }

    public Recepcionista(RecepcionistaId id, Usuario usuario) {
       this.id = id;
       this.usuario = usuario;
    }
   
    public RecepcionistaId getId() {
        return this.id;
    }
    
    public void setId(RecepcionistaId id) {
        this.id = id;
    }
    public Usuario getUsuario() {
        return this.usuario;
    }
    
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }




}


