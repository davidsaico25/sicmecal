package controller;

import com.itextpdf.text.DocumentException;
import dao.DAOCitaMedica;
import dao.DAOEspecialidad;
import dao.DAOHistorialClinico;
import dao.DAOPaciente;
import dao.DAOTurno;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;
import model.CitaMedica;
import model.Especialidad;
import model.HistorialClinico;
import model.Paciente;
import model.Persona;
import model.Turno;
import model.Usuario;
import tool.FileManager;
import tool.SendMailAttachFile;

/**
 *
 * @author davisonsp
 */
@ManagedBean(name = "reservarCitaBean")
@ViewScoped
public class ReservarCitaBean implements Serializable {

    private Usuario usuario;
    
    DAOCitaMedica daoCitaMedica;
    private CitaMedica citaMedica;
    
    DAOPaciente daoPaciente;
    private Paciente paciente;
    
    DAOHistorialClinico daoHistorialClinico;
    private HistorialClinico historialClinico;
    
    DAOTurno daoTurno;
    private Turno selectedTurno;
    private List<Turno> listTurno;
    private List<Turno> filteredListTurno;
    
    DAOEspecialidad daoEspecialidad;
    private List<Especialidad> listEspecialidad;
    
    public ReservarCitaBean() {
        HttpSession httpSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        daoHistorialClinico = new DAOHistorialClinico();
        usuario = (Usuario) httpSession.getAttribute("usuario");
        historialClinico = daoHistorialClinico.getHistorialClinico(usuario);
        
        daoPaciente = new DAOPaciente();
        paciente = daoPaciente.getPaciente((Usuario) httpSession.getAttribute("usuario"));
        
        daoTurno = new DAOTurno();
        listTurno = daoTurno.getListTurno();
        
        daoEspecialidad = new DAOEspecialidad();
        listEspecialidad = daoEspecialidad.getListEspecialidad();
    }
    
    public void reservarCita(ActionEvent actionEvent) {
        String codTurno = String.valueOf(actionEvent.getComponent().getAttributes().get("codTurno"));
        Turno turno = daoTurno.getTurno(codTurno);
        daoCitaMedica = new DAOCitaMedica();
        citaMedica = new CitaMedica();
        citaMedica.setFechaCreacion(new Date());
        citaMedica.setPaciente(paciente);
        citaMedica.setTurno(turno);
        citaMedica.setEstado('P');
        citaMedica.setEstadoPago('P');
        daoCitaMedica.crear(citaMedica);
        double numCitas = turno.getNumCitasMax().doubleValue() - 1;
        turno.setNumCitasMax(BigDecimal.valueOf(numCitas));
        daoTurno.actualizar(turno);
        
        try {
            String file = FileManager.crearTicketCitaMedica(citaMedica);
            SendMailAttachFile sendMailAttachFile = new SendMailAttachFile(paciente.getUsuario().getPersona().getCorreo(), "citaMedica", "Su cita Medica fue registrado con exito. Adjunto documento.", file);
            sendMailAttachFile.send();
        } catch (DocumentException | FileNotFoundException | MessagingException ex) {}
        
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cita Reservada", "");
        FacesContext.getCurrentInstance().addMessage(null, message);
        FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(), null, "/paciente/index.xhtml");
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

    public List<Turno> getListTurno() {
        return listTurno;
    }

    public void setListTurno(List<Turno> listTurno) {
        this.listTurno = listTurno;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public List<Turno> getFilteredListTurno() {
        return filteredListTurno;
    }

    public void setFilteredListTurno(List<Turno> filteredListTurno) {
        this.filteredListTurno = filteredListTurno;
    }

    public Turno getSelectedTurno() {
        return selectedTurno;
    }

    public void setSelectedTurno(Turno selectedTurno) {
        this.selectedTurno = selectedTurno;
    }

    public List<Especialidad> getListEspecialidad() {
        return listEspecialidad;
    }

    public void setListEspecialidad(List<Especialidad> listEspecialidad) {
        this.listEspecialidad = listEspecialidad;
    }
}
