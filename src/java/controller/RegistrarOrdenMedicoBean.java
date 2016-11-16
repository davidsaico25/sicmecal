package controller;

import dao.DAODiagnostico;
import dao.DAOExamenLaboratorio;
import dao.DAOHistorialClinico;
import dao.DAOIndicador;
import dao.DAOMuestra;
import dao.DAOOrdenMedico;
import java.io.Serializable;
import java.util.Date;
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
import model.MuestraId;
import model.OrdenMedico;
import model.Paciente;
import model.Persona;
import model.Usuario;

@ManagedBean(name = "registrarOrdenMedicoBean")
@ViewScoped
public class RegistrarOrdenMedicoBean implements Serializable{
    
    private Persona persona;
    private Usuario usuario;
    private Paciente paciente;
    private HistorialClinico historialClinico;
    private Diagnostico diagnostico;
    private OrdenMedico ordenMedico;
    private ExamenLaboratorio examenLaboratorio;
    private Indicador indicador;
    private List<Indicador> listIndicador;
    private List<ExamenLaboratorio> listExamenLaboratorio;
    private Muestra muestra;
    
    private DAOHistorialClinico daoHistorialClinico;
    private DAODiagnostico daoDiagnostico;
    private DAOExamenLaboratorio daoExamenLaboratorio;
    private DAOIndicador daoIndicador;
    private DAOOrdenMedico daoOrdenMedico;
    private DAOMuestra daoMuestra;
    
    public RegistrarOrdenMedicoBean(){
        persona = new Persona();
        usuario = new Usuario();
        indicador = new Indicador();
        examenLaboratorio = new ExamenLaboratorio();
    }
    
    public void mostrarHC(ActionEvent ae){
        daoHistorialClinico = new DAOHistorialClinico();
        historialClinico = daoHistorialClinico.getHistorialClinicoByPersona(persona);
        persona = historialClinico.getPaciente().getUsuario().getPersona();
        daoDiagnostico = new DAODiagnostico();
        diagnostico = daoDiagnostico.getDiagnosticoByHistorial(historialClinico);
        daoExamenLaboratorio = new DAOExamenLaboratorio();
        listExamenLaboratorio = daoExamenLaboratorio.getListExamenLaboratorio();
        daoIndicador = new DAOIndicador();
        listIndicador = daoIndicador.getListIndicador();
    }
    public void registrar(ActionEvent ae){
        daoOrdenMedico = new DAOOrdenMedico();
        ordenMedico = new OrdenMedico();
        ordenMedico.setCodDiagnostico(diagnostico.getCodDiagnostico());
        ordenMedico.setEstado('P');
        ordenMedico.setFechaEjecucion(new Date());
        ordenMedico.setDiagnostico(diagnostico);
        daoOrdenMedico.crear(ordenMedico);
        
        daoMuestra = new DAOMuestra();
        muestra = new Muestra();
        muestra.setIndicador(indicador);
        muestra.setOrdenMedico(ordenMedico);

        MuestraId muestraid = new MuestraId();
        muestraid.setCodDiagnostico(diagnostico.getCodDiagnostico());
        muestraid.setCodIndicador(indicador.getCodIndicador());
        
        muestra.setId(muestraid);
        daoMuestra.crear(muestra);
        
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Orden Medico Registrado", "");
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

    public OrdenMedico getOrdenMedico() {
        return ordenMedico;
    }

    public void setOrdenMedico(OrdenMedico ordenMedico) {
        this.ordenMedico = ordenMedico;
    }

    public ExamenLaboratorio getExamenLaboratorio() {
        return examenLaboratorio;
    }

    public void setExamenLaboratorio(ExamenLaboratorio examenLaboratorio) {
        this.examenLaboratorio = examenLaboratorio;
    }

    public List<Indicador> getListIndicador() {
        return listIndicador;
    }

    public void setListIndicador(List<Indicador> listIndicador) {
        this.listIndicador = listIndicador;
    }

    /**
     * @return the listExamenLaboratorio
     */
    public List<ExamenLaboratorio> getListExamenLaboratorio() {
        return listExamenLaboratorio;
    }

    /**
     * @param listExamenLaboratorio the listExamenLaboratorio to set
     */
    public void setListExamenLaboratorio(List<ExamenLaboratorio> listExamenLaboratorio) {
        this.listExamenLaboratorio = listExamenLaboratorio;
    }

    /**
     * @return the indicador
     */
    public Indicador getIndicador() {
        return indicador;
    }

    /**
     * @param indicador the indicador to set
     */
    public void setIndicador(Indicador indicador) {
        this.indicador = indicador;
    }

    /**
     * @return the muestra
     */
    public Muestra getMuestra() {
        return muestra;
    }

    /**
     * @param muestra the muestra to set
     */
    public void setMuestra(Muestra muestra) {
        this.muestra = muestra;
    }
    
}
