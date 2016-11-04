package controller;
import dao.DAOTecnologo;
import dao.DAOInsumo;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.servlet.http.HttpSession;
import model.Tecnologo;
import model.Insumo;
import model.Usuario;

/**
 *
 * @author davisonsp
 */
@ManagedBean(name = "consultarStockBean")
@ViewScoped
public class ConsultarStockBean implements Serializable {

    Usuario usuario;
    
    DAOTecnologo daoTecnologo;
    private Tecnologo tecnologo;
          
    DAOInsumo daoInsumo;
    private Insumo selectedInsumo;
    private List<Insumo> listInsumo;
    private List<Insumo> filteredListInsumo;
    
    public ConsultarStockBean() {
        HttpSession httpSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
                
        daoTecnologo = new DAOTecnologo();
        usuario = (Usuario) httpSession.getAttribute("usuario");
        
        daoInsumo = new DAOInsumo();
        listInsumo = daoInsumo.getListInsumo();
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

    public Insumo getSelectedInsumo() {
        return selectedInsumo;
    }

    public void setSelectedInsumo(Insumo selectedInsumo) {
        this.selectedInsumo = selectedInsumo;
    }

    public List<Insumo> getListInsumo() {
        return listInsumo;
    }

    public void setListInsumo(List<Insumo> listInsumo) {
        this.listInsumo = listInsumo;
    }

    public List<Insumo> getFilteredListInsumo() {
        return filteredListInsumo;
    }

    public void setFilteredListInsumo(List<Insumo> filteredListInsumo) {
        this.filteredListInsumo = filteredListInsumo;
    }

}
