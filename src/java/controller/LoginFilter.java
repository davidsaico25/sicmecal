package controller;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuElement;
import org.primefaces.model.menu.MenuModel;

/**
 *
 * @author davisonsp
 */
@WebFilter(filterName = "LoginFilter", urlPatterns = {"/*"})
public class LoginFilter implements Filter {
    
    private static final boolean debug = true;

    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;
    
    public LoginFilter() {
    }    
    
    private void doBeforeProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("LoginFilter:DoBeforeProcessing");
        }

        // Write code here to process the request and/or response before
        // the rest of the filter chain is invoked.
        // For example, a logging filter might log items on the request object,
        // such as the parameters.
        /*
	for (Enumeration en = request.getParameterNames(); en.hasMoreElements(); ) {
	    String name = (String)en.nextElement();
	    String values[] = request.getParameterValues(name);
	    int n = values.length;
	    StringBuffer buf = new StringBuffer();
	    buf.append(name);
	    buf.append("=");
	    for(int i=0; i < n; i++) {
	        buf.append(values[i]);
	        if (i < n-1)
	            buf.append(",");
	    }
	    log(buf.toString());
	}
         */
    }    
    
    private void doAfterProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("LoginFilter:DoAfterProcessing");
        }

        // Write code here to process the request and/or response after
        // the rest of the filter chain is invoked.
        // For example, a logging filter might log the attributes on the
        // request object after the request has been processed. 
        /*
	for (Enumeration en = request.getAttributeNames(); en.hasMoreElements(); ) {
	    String name = (String)en.nextElement();
	    Object value = request.getAttribute(name);
	    log("attribute: " + name + "=" + value.toString());

	}
         */
        // For example, a filter might append something to the response.
        /*
	PrintWriter respOut = new PrintWriter(response.getWriter());
	respOut.println("<P><B>This has been appended by an intrusive filter.</B>");
         */
    }

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        // Obtengo el bean que representa el usuario desde el scope sesión
        LoginBean loginBean = (LoginBean) req.getSession().getAttribute("loginBean");

        //Proceso la URL que está requiriendo el cliente
        String urlStr = req.getRequestURL().toString().toLowerCase();
        
        /*
        boolean toRestrict = restrict(urlStr);
        System.out.println(urlStr + " - restringir? = [" + toRestrict + "]");
        */
        
        //Si no requiere proteccion ingresa a la condicional para continuar con el request
        //Ejm: /faces/index.xhtml (no restringido)
        //Ejm: /faces/user.xhtml (restringido)
        if (!restrict(urlStr)) {
            chain.doFilter(request, response);
            return;
        }
        
        //Es restringido y verificara si esta logueado para aceptar el request
        //Si el usuario no esta logueado entra a la condicional y es redireccionado al index
        if (loginBean == null || !loginBean.isLogged()) {
            res.sendRedirect(req.getContextPath() + "/faces/index.xhtml");
            return;
        }
        
        List<String> listSegmento = getListSegmento(urlStr);
        if (listSegmento.size() > 7) {
            res.sendRedirect(req.getContextPath() + "/faces/" + loginBean.getPerfil() + "/index.xhtml");
            return;
        }
        
        //El usuario esta logueado
        //Verificar que tenga permiso para el modulo
        if (!permitModule(urlStr, loginBean.getPerfil(), req)) {
            res.sendRedirect(req.getContextPath() + "/faces/" + loginBean.getPerfil() + "/index.xhtml");
            return;
        }
        
        //Se acepta el request. El usuario es redireccionado a su request
        chain.doFilter(request, response);
        
        /*
        if (debug) {
            log("LoginFilter:doFilter()");
        }
        
        doBeforeProcessing(request, response);
        
        Throwable problem = null;
        try {
            chain.doFilter(request, response);
        } catch (Throwable t) {
            // If an exception is thrown somewhere down the filter chain,
            // we still want to execute our after processing, and then
            // rethrow the problem after that.
            problem = t;
            t.printStackTrace();
        }
        
        doAfterProcessing(request, response);

        // If there was a problem, we want to rethrow it if it is
        // a known type, otherwise log it.
        if (problem != null) {
            if (problem instanceof ServletException) {
                throw (ServletException) problem;
            }
            if (problem instanceof IOException) {
                throw (IOException) problem;
            }
            sendProcessingError(problem, response);
        }
        */
    }
    
    private boolean restrict(String urlStr) {
        
        /*
         * Este es un buen lugar para colocar y programar todos los patrones que
         * creamos convenientes para determinar cuales de los recursos no
         * requieren protección. Sin duda que habría que crear un mecanizmo tal
         * que se obtengan de un archivo de configuración o algo que no requiera
         * compilación.
         */
        if (urlStr.endsWith("sicmecal/") || urlStr.endsWith("sicmecal/faces/index.xhtml"))
            return false;
        if (urlStr.indexOf("/javax.faces.resource/") != -1)
            return false;
        return true;
    }
    
    private boolean permitModule(String urlStr, String perfil, HttpServletRequest req) {
        String folder = urlStr.substring(urlStr.indexOf("/", urlStr.indexOf("faces")) + 1, urlStr.lastIndexOf("/"));
        String module = urlStr.substring(urlStr.lastIndexOf("/") + 1, urlStr.lastIndexOf("."));
        if (!perfil.equalsIgnoreCase(folder)) {
            return false;
        }
        if (module.equalsIgnoreCase("index")) {
            return true;
        }
        MenuModel menuModel = (MenuModel) req.getSession().getAttribute("menuModel");
        List<MenuElement> listSubMenuElement = menuModel.getElements();
        MenuElement menuElement = null;
        try {
            menuElement = listSubMenuElement.remove(0);
            for (MenuElement subMenuElement : listSubMenuElement) {
                DefaultSubMenu subMenu = (DefaultSubMenu) subMenuElement;
                List<MenuElement> listMenuItemElement = subMenu.getElements();
                for (MenuElement menuItemElement : listMenuItemElement) {
                    DefaultMenuItem menuItem = (DefaultMenuItem) menuItemElement;
                    if (module.equalsIgnoreCase(menuItem.getOutcome())) {
                        return true;
                    }
                }
            }
        } catch (Exception e) {
        } finally {
            listSubMenuElement.add(0, menuElement);
        }
        return false;
    }
    
    public List<String> getListSegmento(String urlStr) {
        List<String> listSegmento = new ArrayList<>();
        String[] segmentos = urlStr.split("/");
        for (String itemSegmento : segmentos) {
            listSegmento.add(itemSegmento);
        }
        return listSegmento;
    }

    /**
     * Return the filter configuration object for this filter.
     */
    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    /**
     * Set the filter configuration object for this filter.
     *
     * @param filterConfig The filter configuration object
     */
    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    /**
     * Destroy method for this filter
     */
    public void destroy() {        
    }

    /**
     * Init method for this filter
     */
    public void init(FilterConfig filterConfig) {        
        this.filterConfig = filterConfig;
        if (filterConfig != null) {
            if (debug) {                
                log("LoginFilter:Initializing filter");
            }
        }
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("LoginFilter()");
        }
        StringBuffer sb = new StringBuffer("LoginFilter(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }
    
    private void sendProcessingError(Throwable t, ServletResponse response) {
        String stackTrace = getStackTrace(t);        
        
        if (stackTrace != null && !stackTrace.equals("")) {
            try {
                response.setContentType("text/html");
                PrintStream ps = new PrintStream(response.getOutputStream());
                PrintWriter pw = new PrintWriter(ps);                
                pw.print("<html>\n<head>\n<title>Error</title>\n</head>\n<body>\n"); //NOI18N

                // PENDING! Localize this for next official release
                pw.print("<h1>The resource did not process correctly</h1>\n<pre>\n");                
                pw.print(stackTrace);                
                pw.print("</pre></body>\n</html>"); //NOI18N
                pw.close();
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        } else {
            try {
                PrintStream ps = new PrintStream(response.getOutputStream());
                t.printStackTrace(ps);
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        }
    }
    
    public static String getStackTrace(Throwable t) {
        String stackTrace = null;
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            pw.close();
            sw.close();
            stackTrace = sw.getBuffer().toString();
        } catch (Exception ex) {
        }
        return stackTrace;
    }
    
    public void log(String msg) {
        filterConfig.getServletContext().log(msg);        
    }
    
}
