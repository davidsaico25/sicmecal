package controller;

import dao.DAOMedico;
import dao.DAOPersona;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import model.Medico;
import model.Paciente;
import model.Persona;

/**
 *
 * @author davisonsp
 */
@ManagedBean(name = "crearDiagnosticoBean")
@ViewScoped
public class CrearDiagnostico implements Serializable {

    DAOMedico daoMedico;
    private Medico medico;
    
    DAOPersona daoPersona;
    private Persona persona;
            
    private Paciente paciente;
    
    public CrearDiagnostico() {
        persona = new Persona();
    }
    
    public void buscarPaciente(ActionEvent actionEvent) {
        daoPersona = new DAOPersona();
        persona = daoPersona.getPersonaByNroDoc(persona);
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }
}
