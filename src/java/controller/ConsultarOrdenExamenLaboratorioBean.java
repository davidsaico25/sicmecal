package controller;
import dao.DAOTecnologo;
import dao.DAOOrdenMedico;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.servlet.http.HttpSession;
import model.Tecnologo;
import model.OrdenMedico;
import model.Usuario;

/**
 *
 * @author davisonsp
 */
@ManagedBean(name = "consultarOrdenExamenLaboratorioBean")
@ViewScoped
public class ConsultarOrdenExamenLaboratorioBean implements Serializable {

    Usuario usuario;
    
    DAOTecnologo daoTecnologo;
    private Tecnologo tecnologo;
          
    DAOOrdenMedico daoOrdenMedico;
    private OrdenMedico selectedOrdenMedico;
    private List<OrdenMedico> listOrdenMedico;
    private List<OrdenMedico> filteredListOrdenMedico;
    
    public ConsultarOrdenExamenLaboratorioBean() {
        HttpSession httpSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
                
        daoTecnologo = new DAOTecnologo();
        usuario = (Usuario) httpSession.getAttribute("usuario");
        
        daoOrdenMedico = new DAOOrdenMedico();
        listOrdenMedico = daoOrdenMedico.getListOrdenMedico();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public Tecnologo getTecnologo() {
        return tecnologo;
    }

    public void setTecnologo(Tecnologo tecnologo) {
        this.tecnologo = tecnologo;
    }

    public OrdenMedico getSelectedOrdenMedico() {
        return selectedOrdenMedico;
    }

    public void setSelectedOrdenMedico(OrdenMedico selectedOrdenMedico) {
        this.selectedOrdenMedico = selectedOrdenMedico;
    }

    public List<OrdenMedico> getListOrdenMedico() {
        return listOrdenMedico;
    }

    public void setListOrdenMedico(List<OrdenMedico> listOrdenMedico) {
        this.listOrdenMedico = listOrdenMedico;
    }

    public List<OrdenMedico> getFilteredListOrdenMedico() {
        return filteredListOrdenMedico;
    }

    public void setFilteredListOrdenMedico(List<OrdenMedico> filteredListOrdenMedico) {
        this.filteredListOrdenMedico = filteredListOrdenMedico;
    }
    
    
}
