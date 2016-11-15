package controller;

import dao.DAOCitaMedica;
import dao.DAOTurno;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import model.CitaMedica;
import model.Persona;
import model.Turno;

/**
 *
 * @author davisonsp
 */
@Named(value = "reprogramarCitaMedicaBean")
@ViewScoped
public class ReprogramarCitaMedicaBean implements Serializable {

    private Persona persona;
    
    DAOCitaMedica daoCitaMedica;
    private List<CitaMedica> listCitaMedica;
    private CitaMedica selectedCitaMedica;
    
    DAOTurno daoTurno;
    private List<Turno> listTurno;
    private List<Turno> filteredListTurno;
    private Turno selectedTurno;
    
    public ReprogramarCitaMedicaBean() {
        persona = new Persona();
        daoCitaMedica = new DAOCitaMedica();
    }
    
    public void buscarListCitaMedica(ActionEvent actionEvent) {
        listCitaMedica = daoCitaMedica.getListCitaMedicaByPersona(persona);
        listTurno = null;
    }
    
    public void buscarListTurnoDisponible(ActionEvent actionEvent) {
        listTurno = daoCitaMedica.getListTurnosReprogramarCitaMedica(selectedCitaMedica);
    }
    
    public void reprogramarCitaMedica(ActionEvent actionEvent) {
        String codTurno = String.valueOf(actionEvent.getComponent().getAttributes().get("codTurno"));
        daoTurno = new DAOTurno();
        
        Turno oldTurno = selectedCitaMedica.getTurno();
        int newCantCitasOldTurno = oldTurno.getNumCitasMax().intValue() + 1;
        oldTurno.setNumCitasMax(new BigDecimal(newCantCitasOldTurno));
        daoTurno.actualizar(oldTurno);
        
        selectedTurno = daoTurno.getTurno(codTurno);
        selectedCitaMedica.setTurno(selectedTurno);
        daoCitaMedica.actualizar(selectedCitaMedica);
        
        int newCantCitasSelectedTurno = selectedTurno.getNumCitasMax().intValue() - 1;
        selectedTurno.setNumCitasMax(new BigDecimal(newCantCitasSelectedTurno));
        daoTurno.actualizar(selectedTurno);
    }
    
    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public List<CitaMedica> getListCitaMedica() {
        return listCitaMedica;
    }

    public void setListCitaMedica(List<CitaMedica> listCitaMedica) {
        this.listCitaMedica = listCitaMedica;
    }

    public CitaMedica getSelectedCitaMedica() {
        return selectedCitaMedica;
    }

    public void setSelectedCitaMedica(CitaMedica selectedCitaMedica) {
        this.selectedCitaMedica = selectedCitaMedica;
    }

    public Turno getSelectedTurno() {
        return selectedTurno;
    }

    public void setSelectedTurno(Turno selectedTurno) {
        this.selectedTurno = selectedTurno;
    }

    public List<Turno> getListTurno() {
        return listTurno;
    }

    public void setListTurno(List<Turno> listTurno) {
        this.listTurno = listTurno;
    }

    public List<Turno> getFilteredListTurno() {
        return filteredListTurno;
    }

    public void setFilteredListTurno(List<Turno> filteredListTurno) {
        this.filteredListTurno = filteredListTurno;
    }
}
