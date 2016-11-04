package controller;

import dao.DAOHistorialClinico;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import model.HistorialClinico;
import model.Paciente;
import model.Persona;
import model.Usuario;

@ManagedBean(name = "registrarRecetaMedicaBean")
@ViewScoped
public class RegistrarRecetaMedicaBean implements Serializable {
    
    private Persona persona;
    private Paciente paciente;
    private Usuario usuario;
    private HistorialClinico historialClinico;
    
    DAOHistorialClinico daoHistorialClinico;
    
    public RegistrarRecetaMedicaBean(){
        persona = new Persona();
        usuario = new Usuario();
        
    }
    
    public void muestraHC(ActionEvent ae){
        daoHistorialClinico = new DAOHistorialClinico();
        historialClinico = daoHistorialClinico.getHistorialClinicoByPersona(persona);
        System.out.println(persona.getNumeroDocumento());
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }
     public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }


    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public HistorialClinico getHistorialClinico() {
        return historialClinico;
    }

    public void setHistorialClinico(HistorialClinico historialClinico) {
        this.historialClinico = historialClinico;
    }
    
}
