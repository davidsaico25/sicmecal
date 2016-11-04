package dao;

import java.io.Serializable;
import model.Diagnostico;
import model.ExamenLaboratorio;
import org.hibernate.Query;
import org.hibernate.Session;

public class DAOExamenLaboratorio extends ADAO_crud<Object> implements Serializable{
   
    public ExamenLaboratorio getExamenLaboratorioByDiagnostico(Diagnostico diagnostico) {
        ExamenLaboratorio examenLaboratorio = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from ExamenLaboratorio el inner join fetch el.indicadors i inner join fetch i.muestras m inner join fetch m.ordenMedico om inner join fetch om.diagnostico d where d.codDiagnostico = :codDiagnostico");
            query.setParameter("codDiagnostico", diagnostico.getCodDiagnostico());
            examenLaboratorio = (ExamenLaboratorio) query.uniqueResult();
            return examenLaboratorio;
        } catch (Exception e) {
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return null;
    }
}
