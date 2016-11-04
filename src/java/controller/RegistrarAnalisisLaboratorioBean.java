package controller;

import dao.DAODiagnostico;
import dao.DAOExamenLaboratorio;
import dao.DAOHistorialClinico;
import dao.DAOIndicador;
import dao.DAOMuestra;
import dao.DAOOrdenMedico;
import dao.DAOPaciente;
import dao.DAOPersona;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import model.Diagnostico;
import model.ExamenLaboratorio;
import model.HistorialClinico;
import model.Indicador;
import model.Muestra;
import model.OrdenMedico;
import model.Paciente;
import model.Persona;
import model.RecetaMedica;
import model.Usuario;

@ManagedBean(name = "registrarAnalisisLaboratorioBean")
@ViewScoped
public class RegistrarAnalisisLaboratorioBean implements Serializable{
    
    private Persona persona;
    private Usuario usuario;
    private Paciente paciente;
    private HistorialClinico historialClinico;
    private Diagnostico diagnostico;
    private RecetaMedica recetaMedica;
    private ExamenLaboratorio examenLaboratorio;
    private Indicador indicador;
    private List<Indicador> listIndicador;
    private Muestra muestra;
    private OrdenMedico ordenMedico;
    
    private DAOHistorialClinico daoHistorialClinico;
    private DAOPaciente daoPaciente;
    private DAOPersona daoPersona;
    private DAODiagnostico daoDiagnostico;
    private DAOExamenLaboratorio daoExamenLaboratorio;
    private DAOIndicador daoIndicador;
    private DAOOrdenMedico daoOrdenMedico;
    private DAOMuestra daoMuestra;
    
    public RegistrarAnalisisLaboratorioBean(){
        persona = new Persona();
        usuario = new Usuario();
    }
    
    public void mostrarHC(ActionEvent actionEvent){
        daoHistorialClinico = new DAOHistorialClinico();
        historialClinico = daoHistorialClinico.getHistorialClinicoByPersona(persona);
        persona = historialClinico.getPaciente().getUsuario().getPersona();
        daoDiagnostico = new DAODiagnostico();
        diagnostico = daoDiagnostico.getDiagnosticoByHistorial(historialClinico);
        daoExamenLaboratorio = new DAOExamenLaboratorio();
        examenLaboratorio = daoExamenLaboratorio.getExamenLaboratorioByDiagnostico(diagnostico);
        daoIndicador = new DAOIndicador();
        listIndicador = daoIndicador.getListIndicador();
    }
    
    public void registrarAnalisis(ActionEvent actionEvent) {
        daoOrdenMedico = new DAOOrdenMedico();
        ordenMedico = daoOrdenMedico.getOrdenMedicobyDiagnostico(diagnostico);
        daoMuestra = new DAOMuestra();
        muestra = new Muestra();
        muestra.setIndicador(indicador);
        muestra.setOrdenMedico(ordenMedico);
        daoMuestra.crear(muestra);
        ordenMedico.setEstado('R');
        daoOrdenMedico.actualizar(ordenMedico);
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Resultado de Examen de Laboratorio Realizado", "");
        FacesContext.getCurrentInstance().addMessage(null, message);
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

    public HistorialClinico getHistorialClinico() {
        return historialClinico;
    }

    public void setHistorialClinico(HistorialClinico historialClinico) {
        this.historialClinico = historialClinico;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Diagnostico getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(Diagnostico diagnostico) {
        this.diagnostico = diagnostico;
    }

    public RecetaMedica getRecetaMedica() {
        return recetaMedica;
    }

    public void setRecetaMedica(RecetaMedica recetaMedica) {
        this.recetaMedica = recetaMedica;
    }

    public ExamenLaboratorio getExamenLaboratorio() {
        return examenLaboratorio;
    }

    public void setExamenLaboratorio(ExamenLaboratorio examenLaboratorio) {
        this.examenLaboratorio = examenLaboratorio;
    }

    public Indicador getIndicador() {
        return indicador;
    }

    public void setIndicador(Indicador indicador) {
        this.indicador = indicador;
    }

    public Muestra getMuestra() {
        return muestra;
    }

    public void setMuestra(Muestra muestra) {
        this.muestra = muestra;
    }

    /**
     * @return the listIndicador
     */
    public List<Indicador> getListIndicador() {
        return listIndicador;
    }

    /**
     * @param listIndicador the listIndicador to set
     */
    public void setListIndicador(List<Indicador> listIndicador) {
        this.listIndicador = listIndicador;
    }

}
