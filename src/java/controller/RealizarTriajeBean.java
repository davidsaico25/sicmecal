package controller;

import dao.DAOCitaMedica;
import dao.DAODiagnostico;
import dao.DAOHistorialClinico;
import dao.DAOTriaje;
import java.io.Serializable;
import java.util.Date;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import model.CitaMedica;
import model.Diagnostico;
import model.Enfermero;
import model.HistorialClinico;
import model.Paciente;
import model.Persona;
import model.Triaje;
import model.Usuario;

/**
 *
 * @author davisonsp
 */
@Named(value = "realizarTriajeBean")
@ViewScoped
public class RealizarTriajeBean implements Serializable {

    private Enfermero enfermero;
    
    private Usuario usuario;
    
    private Paciente paciente;
    
    private Persona persona;
    
    private DAOHistorialClinico daoHistorialClinico;
    private HistorialClinico historialClinico;
    
    private DAODiagnostico daoDiagnostico;
    private Diagnostico diagnostico;
    
    private DAOTriaje daoTriaje;
    private Triaje triaje;
    
    private String numeroDocumento;
    
    public RealizarTriajeBean() {
        HttpSession httpSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        usuario = (Usuario) httpSession.getAttribute("usuario");
        enfermero = usuario.getEnfermero();
        triaje = new Triaje();
    }
    
    public void buscarHistorialClinico(ActionEvent actionEvent) {
        daoHistorialClinico = new DAOHistorialClinico();
        historialClinico = daoHistorialClinico.getHistorialClinicoByDNI(numeroDocumento);
        paciente = historialClinico.getPaciente();
        persona = paciente.getUsuario().getPersona();
    }
    
    public void registrarTriaje(ActionEvent actionEvent) {
        daoDiagnostico = new DAODiagnostico();
        diagnostico = new Diagnostico();
        diagnostico.setFecha(new Date());
        diagnostico.setHistorialClinico(historialClinico);
        daoDiagnostico.crear(diagnostico);
        daoTriaje = new DAOTriaje();
        triaje.setCodDiagnostico(diagnostico.getCodDiagnostico());
        triaje.setDiagnostico(diagnostico);
        triaje.setEnfermera(enfermero.getUsername());
        daoTriaje.crear(triaje);
    }
    
    public void verificar(ActionEvent actionEvent) {
        DAOCitaMedica dao = new DAOCitaMedica();
        CitaMedica cita = dao.getCitaMedicaParaTriaje();
        System.out.println(cita.getCodCitaMedica());
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Triaje getTriaje() {
        return triaje;
    }

    public void setTriaje(Triaje triaje) {
        this.triaje = triaje;
    }
}
