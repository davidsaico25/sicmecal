package dao;

import java.io.Serializable;
import model.Diagnostico;
import model.OrdenMedico;
import org.hibernate.Query;
import org.hibernate.Session;

public class DAOOrdenMedico extends ADAO_crud<Object> implements Serializable{
    
    public OrdenMedico getOrdenMedicobyDiagnostico(Diagnostico diagnostico) {
        OrdenMedico ordenMedico = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from OrdenMedico om inner join fetch om.diagnostico d where d.codDiagnostico = :codDiagnostic");
            query.setParameter("codDiagnostic", diagnostico.getCodDiagnostico());
            ordenMedico = (OrdenMedico) query.uniqueResult();
            return ordenMedico;
        } catch (Exception e) {
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return null;
    }
    
}
