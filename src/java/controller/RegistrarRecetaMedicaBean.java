package controller;

import dao.DAODetalleRecetaMedica;
import dao.DAODiagnostico;
import dao.DAOHistorialClinico;
import dao.DAOMedicamento;
import dao.DAORecetaMedica;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import model.DetalleRecetaMedica;
import model.DetalleRecetaMedicaId;
import model.Diagnostico;
import model.HistorialClinico;
import model.Medicamento;
import model.Paciente;
import model.Persona;
import model.RecetaMedica;
import model.Usuario;

@ManagedBean(name = "registrarRecetaMedicaBean")
@ViewScoped
public class RegistrarRecetaMedicaBean implements Serializable {
    
    private Persona persona;
    private Paciente paciente;
    private Usuario usuario;
    private HistorialClinico historialClinico;
    private Diagnostico diagnostico;
    private RecetaMedica recetaMedica;
    private Medicamento medicamento;
    private List<Medicamento> listMedicamento;
    private DetalleRecetaMedica detalleRecetaMedica;

    DAOHistorialClinico daoHistorialClinico;
    DAODiagnostico daoDiagnostico;
    DAOMedicamento daoMedicamento;
    DAORecetaMedica daoRecetaMedica;
    DAODetalleRecetaMedica daoDetalleRecetaMedica;

    public RegistrarRecetaMedicaBean() {
        persona = new Persona();
        usuario = new Usuario();
        medicamento = new Medicamento();
        detalleRecetaMedica = new DetalleRecetaMedica();
    }

    public void muestraHC(ActionEvent ae) {
        daoHistorialClinico = new DAOHistorialClinico();
        historialClinico = daoHistorialClinico.getHistorialClinicoByPersona(persona);
        persona = historialClinico.getPaciente().getUsuario().getPersona();
        daoMedicamento = new DAOMedicamento();
        listMedicamento = daoMedicamento.getListMedicamento();
        daoDiagnostico = new DAODiagnostico();
        diagnostico = daoDiagnostico.getDiagnosticoByHistorial(historialClinico);
    }

    public void registrarRecetaMedica(ActionEvent actE) {

        daoRecetaMedica = new DAORecetaMedica();
        recetaMedica = new RecetaMedica();
        recetaMedica.setCodDiagnostico(diagnostico.getCodDiagnostico());
        recetaMedica.setDiagnostico(diagnostico);
        daoRecetaMedica.crear(recetaMedica);
        
        daoDetalleRecetaMedica = new DAODetalleRecetaMedica();
        detalleRecetaMedica.setMedicamento(medicamento);
        detalleRecetaMedica.setRecetaMedica(recetaMedica);
        System.out.println(detalleRecetaMedica.getCantidad());
        detalleRecetaMedica.setCantidad(detalleRecetaMedica.getCantidad());
        detalleRecetaMedica.setIndicacion(detalleRecetaMedica.getIndicacion());
        
        DetalleRecetaMedicaId detalleRecetaMedicaId = new DetalleRecetaMedicaId();
        detalleRecetaMedicaId.setCodDiagnostico(diagnostico.getCodDiagnostico());
        detalleRecetaMedicaId.setCodMedicamento(medicamento.getCodMedicamento());
        detalleRecetaMedica.setId(detalleRecetaMedicaId);
        daoRecetaMedica.crear(detalleRecetaMedica);
        System.out.println("/////////////");
        
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Receta Medica Registrada", "");
        FacesContext.getCurrentInstance().addMessage(null, message);
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

    /**
     * @return the diagnostico
     */
    public Diagnostico getDiagnostico() {
        return diagnostico;
    }

    /**
     * @param diagnostico the diagnostico to set
     */
    public void setDiagnostico(Diagnostico diagnostico) {
        this.diagnostico = diagnostico;
    }

    /**
     * @return the recetaMedica
     */
    public RecetaMedica getRecetaMedica() {
        return recetaMedica;
    }

    /**
     * @param recetaMedica the recetaMedica to set
     */
    public void setRecetaMedica(RecetaMedica recetaMedica) {
        this.recetaMedica = recetaMedica;
    }

    /**
     * @return the medicamento
     */
    public Medicamento getMedicamento() {
        return medicamento;
    }

    /**
     * @param medicamento the medicamento to set
     */
    public void setMedicamento(Medicamento medicamento) {
        this.medicamento = medicamento;
    }

    /**
     * @return the listMedicamento
     */
    public List<Medicamento> getListMedicamento() {
        return listMedicamento;
    }

    /**
     * @param listMedicamento the listMedicamento to set
     */
    public void setListMedicamento(List<Medicamento> listMedicamento) {
        this.listMedicamento = listMedicamento;
    }

    /**
     * @return the detalleRecetaMedica
     */
    public DetalleRecetaMedica getDetalleRecetaMedica() {
        return detalleRecetaMedica;
    }

    /**
     * @param detalleRecetaMedica the detalleRecetaMedica to set
     */
    public void setDetalleRecetaMedica(DetalleRecetaMedica detalleRecetaMedica) {
        this.detalleRecetaMedica = detalleRecetaMedica;
    }

}