package controller;

import dao.DAOHistorialClinico;
import dao.DAOPaciente;
import dao.DAOPersona;
import dao.DAOUsuario;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import model.HistorialClinico;
import model.Paciente;
import model.Perfil;
import model.Persona;
import model.Usuario;
import org.primefaces.event.FlowEvent;
 
@ManagedBean(name = "registrarHistorialClinicoBean")
@ViewScoped
public class RegistrarHistorialClinicoBean implements Serializable {
 
    private Persona persona;
    
    private Usuario usuario;
    
    private Paciente paciente;
    
    private HistorialClinico historialClinico;
    
    String maxDate;
    private boolean skip;

    public RegistrarHistorialClinicoBean() {
        persona = new Persona();
        usuario = new Usuario();
        paciente = new Paciente();
        historialClinico = new HistorialClinico();
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date date = new Date();
            maxDate = simpleDateFormat.format(date);
        } catch (Exception ex) {}
    }
    
    public String registrarHistorialClinico() {
        DAOPersona daoPersona = new DAOPersona();
        DAOUsuario daoUsuario = new DAOUsuario();
        DAOPaciente daoPaciente = new DAOPaciente();
        DAOHistorialClinico daoHistorialClinico = new DAOHistorialClinico();
        daoPersona.crear(persona);
        Perfil perfil = new Perfil();
        perfil.setCodPerfil(BigDecimal.valueOf(2));
        usuario.setUsername(persona.getNumeroDocumento());
        usuario.setPerfil(perfil);
        usuario.setPersona(persona);
        usuario.setEstado('A');
        usuario.setFechaCreacion(new Date());
        daoUsuario.crear(usuario);
        paciente.setUsuario(usuario);
        paciente.setEstado('A');
        daoPaciente.crear(paciente);
        historialClinico.setNumeroHistorialClinico(persona.getNumeroDocumento());
        historialClinico.setPaciente(paciente);
        daoHistorialClinico.crear(historialClinico);
        FacesMessage msg = new FacesMessage("Registro", "Paciente :" + persona.getNombre() + " " + persona.getApellidop() + " registrado");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        return "index";
    }

    public Persona getPersona() {
        return persona;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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

    public HistorialClinico getHistorialClinico() {
        return historialClinico;
    }

    public void setHistorialClinico(HistorialClinico historialClinico) {
        this.historialClinico = historialClinico;
    }
     
    public void save() {
        FacesMessage msg = new FacesMessage("Successful", "Registrado");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public String getMaxDate() {
        return maxDate;
    }

    public void setMaxDate(String maxDate) {
        this.maxDate = maxDate;
    }
    
    public boolean isSkip() {
        return skip;
    }
 
    public void setSkip(boolean skip) {
        this.skip = skip;
    }
     
    public String onFlowProcess(FlowEvent event) {
        if(skip) {
            skip = false;   //reset in case user goes back
            return "confirm";
        }
        else {
            return event.getNewStep();
        }
    }
}