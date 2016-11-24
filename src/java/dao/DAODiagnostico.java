package dao;

import java.io.Serializable;
import model.Diagnostico;
import model.HistorialClinico;
import org.hibernate.Query;
import org.hibernate.Session;

public class DAODiagnostico extends ADAO_crud<Object> implements Serializable{
    
    public Diagnostico getDiagnosticoByHistorial(HistorialClinico historialClinico) {
        Diagnostico diagnostico = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from Diagnostico d inner join fetch d.historialClinico hc  where hc.numeroHistorialClinico = :nroHistorialClinico");
            query.setParameter("nroHistorialClinico", historialClinico.getNumeroHistorialClinico());
            diagnostico = (Diagnostico) query.uniqueResult();
            return diagnostico;
        } catch (Exception e) {
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return null;
    }
    
    public Diagnostico getDiagnosticoByTriaje(String nroDocumento) {
        Diagnostico diagnostico = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from Diagnostico d inner join fetch d.historialClinico hc where hc.numeroHistorialClinico = :nroDocumento and d.descripcion is null");
            query.setParameter("nroDocumento", nroDocumento);
            diagnostico = (Diagnostico) query.uniqueResult();
            return diagnostico;
        } catch (Exception e) {
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return null;
    }
}
