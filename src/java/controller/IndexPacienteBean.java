package controller;

import dao.DAOCitaMedica;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import model.CitaMedica;
import model.Persona;
import model.Usuario;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.LazyScheduleModel;
import org.primefaces.model.ScheduleModel;

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
    
    private ScheduleModel lazyEventModel;
    
    public IndexPacienteBean() {
        HttpSession httpSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        persona = ((Usuario) httpSession.getAttribute("usuario")).getPersona();
        daoCitaMedica = new DAOCitaMedica();
        listCitaMedica = daoCitaMedica.getListCitaMedicaByPersona(persona);
        
        lazyEventModel = new LazyScheduleModel() {
            @Override
            public void loadEvents(Date start, Date end) {
                for (CitaMedica citaMedica : listCitaMedica) {
                    addEvent(new DefaultScheduleEvent(citaMedica.getTurno().getConsultorio(), citaMedica.getTurno().getHoraInicio(), citaMedica.getTurno().getHoraFin()));
                }
            }   
        };
    }

    public ScheduleModel getLazyEventModel() {
        return lazyEventModel;
    }

    public void setLazyEventModel(ScheduleModel lazyEventModel) {
        this.lazyEventModel = lazyEventModel;
    }
}
