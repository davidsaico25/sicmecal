package dao;

import java.io.Serializable;
import model.Diagnostico;
import model.RecetaMedica;
import org.hibernate.Query;
import org.hibernate.Session;

public class DAORecetaMedica extends ADAO_crud<Object> implements Serializable {
    
    public RecetaMedica getRecetaMedicabyHistorialClinico(Diagnostico diagnostico){
        RecetaMedica recetaMedica = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from RecetaMedica rm inner join fetch rm.diagnostico d where d.codDiagnostico = :codDiagnostico");
            query.setParameter("codDiagnostico", diagnostico.getCodDiagnostico());
            recetaMedica = (RecetaMedica) query.uniqueResult();
            return recetaMedica;
        } catch (Exception e) {
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return null;
    }
    
}
