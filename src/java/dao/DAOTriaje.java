package dao;

import java.io.Serializable;
import java.math.BigDecimal;
import model.Triaje;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author davisonsp
 */
public class DAOTriaje extends ADAO_crud<Object> implements Serializable {
    
    public Triaje getTriajeByPersona(BigDecimal codDiagnostico) {
        Triaje triaje = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from Triaje t where t.codDiagnostico = :codDiagnostico");
            query.setParameter("codDiagnostico", codDiagnostico);
            triaje = (Triaje) query.uniqueResult();
            return triaje;
        } catch (Exception e) {
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return null;
    }
}
