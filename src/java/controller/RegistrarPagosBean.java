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
import model.CitaMedica;
import model.HistorialClinico;

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
     
    public RegistrarPagosBean(){
        daoCitaMedica = new DAOCitaMedica();
        listCitaMedica = daoCitaMedica.getListCitaMedica();
    }

    public void registrarPagos(ActionEvent actionEvent) {
        String codCitaMedica = String.valueOf(actionEvent.getComponent().getAttributes().get("codCitaMedica"));
        String nroDocumento = String.valueOf(actionEvent.getComponent().getAttributes().get("nroDocumento"));
        citaMedica = new CitaMedica(); 
        
        setCitaMedica(daoCitaMedica.getCitaMedicabyCod(codCitaMedica));
        getCitaMedica().setEstadoPago('C');
        daoCitaMedica.actualizar(getCitaMedica());
        
        daoHistorialClinico = new DAOHistorialClinico();
        historialClinico = daoHistorialClinico.getHistorialClinicoByDNI(nroDocumento);
        
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
