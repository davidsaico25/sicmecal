package controller;

import dao.DAOCitaMedica;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;
import model.CitaMedica;
import model.Persona;
import model.Usuario;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.LazyScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author davisonsp
 */
@ManagedBean(name = "indexPacienteBean")
@ViewScoped
public class IndexPacienteBean implements Serializable {

    Persona persona;
    
    DAOCitaMedica daoCitaMedica;
    private List<CitaMedica> listCitaMedica;
    private CitaMedica citaMedica;
    
    private StreamedContent file;
    
    private ScheduleModel lazyEventModel;
    
    private ScheduleEvent event = new DefaultScheduleEvent();
    
    public IndexPacienteBean() {
        HttpSession httpSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        persona = ((Usuario) httpSession.getAttribute("usuario")).getPersona();
        daoCitaMedica = new DAOCitaMedica();
        listCitaMedica = daoCitaMedica.getListCitaMedicaByPersona(persona);
        
        lazyEventModel = new LazyScheduleModel() {
            @Override
            public void loadEvents(Date start, Date end) {
                for (CitaMedica citaMedica : listCitaMedica) {
                    addEvent(new DefaultScheduleEvent(citaMedica.getTurno().getConsultorio(), citaMedica.getTurno().getHoraInicio(), citaMedica.getTurno().getHoraFin(), citaMedica));
                }
            }   
        };
        
    }
    
    public void download(ActionEvent actionEvent) {
        InputStream stream;
        try {
            stream = new FileInputStream("C:\\Users\\davisonsp\\Documents\\NetBeansProjects\\sicmecal\\docs\\citas medicas\\"+ persona.getNumeroDocumento() + "_" + citaMedica.getCodCitaMedica() + ".pdf");
            file = new DefaultStreamedContent(stream, "application/pdf", persona.getNumeroDocumento() + "_" + citaMedica.getCodCitaMedica() + ".pdf");
        } catch (Exception ex) {}
    }
    
    public void onEventSelect(SelectEvent selectEvent) {
        event = (ScheduleEvent) selectEvent.getObject();
        citaMedica = (CitaMedica) event.getData();
    }

    public ScheduleModel getLazyEventModel() {
        return lazyEventModel;
    }

    public void setLazyEventModel(ScheduleModel lazyEventModel) {
        this.lazyEventModel = lazyEventModel;
    }

    public ScheduleEvent getEvent() {
        return event;
    }

    public void setEvent(ScheduleEvent event) {
        this.event = event;
    }

    public CitaMedica getCitaMedica() {
        return citaMedica;
    }

    public void setCitaMedica(CitaMedica citaMedica) {
        this.citaMedica = citaMedica;
    }

    public StreamedContent getFile() {
        return file;
    }

    public void setFile(StreamedContent file) {
        this.file = file;
    }
}
