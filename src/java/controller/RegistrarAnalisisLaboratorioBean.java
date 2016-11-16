package controller;

import dao.DAODiagnostico;
import dao.DAOExamenLaboratorio;
import dao.DAOHistorialClinico;
import dao.DAOIndicador;
import dao.DAOMuestra;
import dao.DAOOrdenMedico;
import java.io.Serializable;
import java.util.ArrayList;
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
    private List<ExamenLaboratorio> listExamenLaboratorio;
    private Muestra muestra;
    private OrdenMedico ordenMedico;
    
    private String muestraExamen;
    private List<String> listaMuestraExamen;
    
    private DAOHistorialClinico daoHistorialClinico;
    private DAODiagnostico daoDiagnostico;
    private DAOExamenLaboratorio daoExamenLaboratorio;
    private DAOIndicador daoIndicador;
    private DAOOrdenMedico daoOrdenMedico;
    private DAOMuestra daoMuestra;
    
    public RegistrarAnalisisLaboratorioBean(){
        persona = new Persona();
        usuario = new Usuario();
        listaMuestraExamen = new ArrayList<>();
        indicador = new Indicador();
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
        indicador = daoIndicador.getIndicadorByDiagnostico(diagnostico);
    }
    
    public void agregarListaMuestra(ActionEvent ae){
        listaMuestraExamen.add(muestraExamen);
        muestraExamen = "";
    }
    
    public void registrarAnalisis(ActionEvent actEvent) {
        String resultadoMuestra = "";
        for(int i = 0; i < listaMuestraExamen.size(); i++){
            resultadoMuestra += listaMuestraExamen.get(i) + "\n";
        }
        daoOrdenMedico = new DAOOrdenMedico();
        setOrdenMedico(daoOrdenMedico.getOrdenMedicobyDiagnostico(diagnostico));
        
        getOrdenMedico().setEstado('R');
        daoOrdenMedico.actualizar(getOrdenMedico());
        
        daoMuestra = new DAOMuestra();
        setMuestra(daoMuestra.getMuestrabyOrdenMedico(ordenMedico));
        
        getMuestra().setResultadoMuestra(resultadoMuestra);
        daoMuestra.actualizar(getMuestra());
        
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
    
    public List<Indicador> getListIndicador() {
        return listIndicador;
    }

    public void setListIndicador(List<Indicador> listIndicador) {
        this.listIndicador = listIndicador;
    }

    public OrdenMedico getOrdenMedico() {
        return ordenMedico;
    }

    public void setOrdenMedico(OrdenMedico ordenMedico) {
        this.ordenMedico = ordenMedico;
    }

    public String getMuestraExamen() {
        return muestraExamen;
    }

    public void setMuestraExamen(String muestraExamen) {
        this.muestraExamen = muestraExamen;
    }

    public List<String> getListaMuestraExamen() {
        return listaMuestraExamen;
    }

    public void setListaMuestraExamen(List<String> listaMuestraExamen) {
        this.listaMuestraExamen = listaMuestraExamen;
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

}
