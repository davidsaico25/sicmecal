package dao;

import java.io.Serializable;
import model.HistorialClinico;
import model.Persona;
import model.Usuario;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author davisonsp
 */
public class DAOHistorialClinico extends ADAO_crud<Object> implements Serializable {
    
    public HistorialClinico getHistorialClinico(Usuario usuario) {
        HistorialClinico historialClinico = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from HistorialClinico hc inner join fetch hc.paciente p where p.username = :username");
            query.setParameter("username", usuario.getUsername());
            historialClinico = (HistorialClinico) query.uniqueResult();
            return historialClinico;
        } catch (Exception e) {
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return null;
    }
    
    public HistorialClinico getHistorialClinicoByPersona(Persona persona) {
        HistorialClinico historialClinico = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from HistorialClinico hc inner join fetch hc.paciente pa inner join fetch pa.usuario u inner join fetch u.persona p where p.numeroDocumento = :nroDocumento");
            query.setParameter("nroDocumento", persona.getNumeroDocumento());
            historialClinico = (HistorialClinico) query.uniqueResult();
            return historialClinico;
        } catch (Exception e) {
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return null;
    }
    
    public HistorialClinico getHistorialClinicoByDNI(String nroDocumento) {
        HistorialClinico historialClinico = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from HistorialClinico hc inner join fetch hc.paciente pa inner join fetch pa.usuario u inner join fetch u.persona p where p.numeroDocumento = :nroDocumento");
            query.setParameter("nroDocumento", nroDocumento);
            historialClinico = (HistorialClinico) query.uniqueResult();
            return historialClinico;
        } catch (Exception e) {
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return null;
    }
}
