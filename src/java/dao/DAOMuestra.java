package dao;

import java.io.Serializable;
import model.Muestra;
import model.OrdenMedico;
import org.hibernate.Query;
import org.hibernate.Session;

public class DAOMuestra extends ADAO_crud<Object> implements Serializable{
    
    public Muestra getMuestrabyOrdenMedico(OrdenMedico ordenMedico) {
        Muestra muestra = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from Muestra m inner join fetch m.ordenMedico om where om.codDiagnostico = :codDiagnostic");
            query.setParameter("codDiagnostic", ordenMedico.getCodDiagnostico());
            muestra = (Muestra) query.uniqueResult();
            return muestra;
        } catch (Exception e) {
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return null;
    }
}
