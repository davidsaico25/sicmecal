package controller;

import dao.DAOCitaMedica;
import dao.DAOHistorialClinico;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;
import model.CitaMedica;
import model.HistorialClinico;
import model.Paciente;
import model.Usuario;

@ManagedBean(name = "registrarPagosBean")
@ViewScoped
public class RegistrarPagosBean implements Serializable{
    DAOCitaMedica daoCitaMedica;
    private CitaMedica citaMedica;
    private CitaMedica selectedCitaMedica;
    private List<CitaMedica> listCitaMedica;
    private List<CitaMedica> filteredListCitaMedica;
    
    DAOHistorialClinico daoHistorialClinico;
    private HistorialClinico historialClinico;
    
    private Usuario usuario;
     
    private Paciente paciente;
     
    public RegistrarPagosBean(){
        usuario = new Usuario();
        daoCitaMedica = new DAOCitaMedica();
        listCitaMedica = daoCitaMedica.getListCitaMedica();
        paciente = new Paciente();
    }

    public void registrarPagos(ActionEvent actionEvent) {
        String codCitaMedica = String.valueOf(actionEvent.getComponent().getAttributes().get("codCitaMedica"));
        
        HttpSession httpSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        usuario = (Usuario) httpSession.getAttribute("usuario");
        
        citaMedica = new CitaMedica(); 
        
        setCitaMedica(daoCitaMedica.getCitaMedicabyCod(codCitaMedica));
        getCitaMedica().setEstadoPago('C');
        getCitaMedica().setCajero(usuario.getPersona().getNombre());
        daoCitaMedica.actualizar(getCitaMedica());
        
        paciente = citaMedica.getPaciente();
        
        System.out.println(paciente.getUsuario().getPersona().getCorreo());
        
//        try {
//            String file = FileManager.crearTicketPagoCita(citaMedica);
//            SendMailAttachFile sendMailAttachFile = new SendMailAttachFile(paciente.getUsuario().getPersona().getCorreo(), "citaMedica", "Su cita Medica fue pagada. Adjunto documento.", file);
//            sendMailAttachFile.send();
//        } catch (DocumentException | FileNotFoundException | MessagingException ex) {}
        
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Pago realizado de la Cita", "");
        FacesContext.getCurrentInstance().addMessage(null, message);
        FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(), null, "/cajero/index.xhtml");
    }

    public CitaMedica getSelectedCitaMedica() {
        return selectedCitaMedica;
    }

    public void setSelectedCitaMedica(CitaMedica selectedCitaMedica) {
        this.selectedCitaMedica = selectedCitaMedica;
    }

    public CitaMedica getCitaMedica() {
        return citaMedica;
    }

    public void setCitaMedica(CitaMedica citaMedica) {
        this.citaMedica = citaMedica;
    }
    
    public List<CitaMedica> getListCitaMedica() {
        return listCitaMedica;
    }

    public void setListCitaMedica(List<CitaMedica> listCitaMedica) {
        this.listCitaMedica = listCitaMedica;
    }

    public List<CitaMedica> getFilteredListCitaMedica() {
        return filteredListCitaMedica;
    }

    public void setFilteredListCitaMedica(List<CitaMedica> filteredListCitaMedica) {
        this.filteredListCitaMedica = filteredListCitaMedica;
    }

    public HistorialClinico getHistorialClinico() {
        return historialClinico;
    }

    public void setHistorialClinico(HistorialClinico historialClinico) {
        this.historialClinico = historialClinico;
    }
}
