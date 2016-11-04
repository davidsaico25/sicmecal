package controller;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.servlet.http.HttpSession;
import model.Usuario;

/**
 *
 * @author davisonsp
 */
@ManagedBean(name = "userBean")
@ViewScoped
public class UserBean implements Serializable {

    HttpSession httpSession;
    Usuario usuario;
    
    public UserBean() {
        httpSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        usuario = (Usuario) httpSession.getAttribute("usuario");
    }
}
