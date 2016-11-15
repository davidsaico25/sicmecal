package controller;

import dao.DAOModulo;
import dao.DAOUsuario;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import model.Modulo;
import model.SubModulo;
import model.Usuario;
import org.primefaces.context.RequestContext;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

/**
 *
 * @author davisonsp
 */
@ManagedBean(name = "loginBean")
@SessionScoped
public class LoginBean implements Serializable {

    DAOUsuario daoUsuario;
    private Usuario usuario;
    
    DAOModulo daoModulo;
    private List<Modulo> listModulo;
    
    private HttpSession httpSession;
    
    private MenuModel menuModel;
    
    private boolean logged = false;
    private String perfil = "";
    
    public LoginBean() {
        usuario = new Usuario();
    }
    
    public String login() {
        String outcome = "";
        RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage message = null;
        daoUsuario = new DAOUsuario();
        usuario = daoUsuario.login(usuario);
        if (usuario != null) {
            daoModulo = new DAOModulo();
            listModulo = daoModulo.getListModuloForUsuario(usuario);
            menuModel = buildMenuModel(listModulo);
            logged = true;
            perfil = usuario.getPerfil().getNombre();
            httpSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
            httpSession.setAttribute("usuario", this.usuario);
            httpSession.setAttribute("menuModel", this.menuModel);
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenido", usuario.getUsername());
            outcome = perfil;
        } else {
            logged = false;
            perfil = "";
            usuario = new Usuario();
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Loggin Error", "Credenciales invalidas");
        }
        FacesContext.getCurrentInstance().addMessage(null, message);
        context.addCallbackParam("loggedIn", logged);
        return outcome;
    }
    
    public String logout() {
        httpSession.removeAttribute("usuario");
        httpSession.removeAttribute("menuModel");
        httpSession.invalidate();
        logged = false;
        return "index";
    }
    
    public MenuModel buildMenuModel(List<Modulo> listModulo) {
        MenuModel model = new DefaultMenuModel();
        
        DefaultMenuItem defaultMenuItem = new DefaultMenuItem();
        defaultMenuItem.setTitle("Inicio");
        defaultMenuItem.setIcon("fa fa-home");
        defaultMenuItem.setOutcome("index");
        model.addElement(defaultMenuItem);

        for (Modulo modulo : listModulo) {
            DefaultSubMenu subMenu = new DefaultSubMenu(modulo.getNombre());
            if (modulo.getSubModulos() != null) {
                for (Object subModuloItem : modulo.getSubModulos()) {
                    SubModulo subModulo = (SubModulo) subModuloItem;
                    DefaultMenuItem menuItem = new DefaultMenuItem(subModulo.getNombre());
                    menuItem.setOutcome(subModulo.getDireccion());
                    subMenu.addElement(menuItem);
                }
            }
            model.addElement(subMenu);
        }
        return model;
    }
    
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public boolean isLogged() {
        return logged;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public void setLogged(boolean logged) {
        this.logged = logged;
    }

    public MenuModel getMenuModel() {
        return menuModel;
    }

    public void setMenuModel(MenuModel menuModel) {
        this.menuModel = menuModel;
    }

    public HttpSession getHttpSession() {
        return httpSession;
    }
}
