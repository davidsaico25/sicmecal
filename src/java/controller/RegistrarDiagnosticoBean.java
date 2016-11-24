
package controller;

import dao.DAODiagnostico;
import dao.DAOHistorialClinico;
import dao.DAOPersona;
import dao.DAOTriaje;
import java.io.Serializable;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;
import model.Diagnostico;
import model.HistorialClinico;
import model.Paciente;
import model.Persona;
import model.Triaje;
import model.Usuario;

@ManagedBean(name = "registrarDiagnosticoBean")
@RequestScoped
public class RegistrarDiagnosticoBean implements Serializable{
    
    DAOPersona daoPersona;
    private Persona persona;
    
    private DAOHistorialClinico daoHistorialClinico;
    private HistorialClinico historialClinico;
    
    private DAOTriaje daoTriaje;
    private Triaje triaje;
    
    private DAODiagnostico daoDiagnostico;
    private Diagnostico diagnostico;
    
    private Paciente paciente;
    private Usuario usuario;
    
    public RegistrarDiagnosticoBean() {
        persona = new Persona();
        usuario = new Usuario();
        diagnostico = new Diagnostico();
        triaje = new Triaje();
    }

    public void mostrarHC(ActionEvent actionEvent){
        daoHistorialClinico = new DAOHistorialClinico();
        historialClinico = daoHistorialClinico.getHistorialClinicoByPersona(persona);
        persona = historialClinico.getPaciente().getUsuario().getPersona();
        
        daoDiagnostico = new DAODiagnostico();
        diagnostico = daoDiagnostico.getDiagnosticoByTriaje(persona.getNumeroDocumento());
        daoTriaje = new DAOTriaje();
        triaje = daoTriaje.getTriajeByPersona(diagnostico.getCodDiagnostico());
        
    }
    
    public void registrar(ActionEvent actionEvent){
        String nroDocumento = persona.getNumeroDocumento();
        HttpSession httpSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        usuario = (Usuario) httpSession.getAttribute("usuario");
        
        daoDiagnostico = new DAODiagnostico();
        String descripcionDiagnostico = diagnostico.getDescripcion();
        
        setDiagnostico(daoDiagnostico.getDiagnosticoByTriaje(nroDocumento));
        getDiagnostico().setDescripcion(descripcionDiagnostico);        
        getDiagnostico().setMedico(usuario.getUsername());
        daoDiagnostico.actualizar(getDiagnostico());
        
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Diagnostico Registrado", "");
        FacesContext.getCurrentInstance().addMessage(null, message);
        FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(), null, "/medico/index.xhtml");
    }
    
    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Triaje getTriaje() {
        return triaje;
    }

    public void setTriaje(Triaje triaje) {
        this.triaje = triaje;
    }

    
    public HistorialClinico getHistorialClinico() {
        return historialClinico;
    }

    public void setHistorialClinico(HistorialClinico historialClinico) {
        this.historialClinico = historialClinico;
    }

    public Diagnostico getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(Diagnostico diagnostico) {
        this.diagnostico = diagnostico;
    }
}
