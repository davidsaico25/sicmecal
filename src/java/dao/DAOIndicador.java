package dao;

import java.io.Serializable;
import java.util.List;
import model.Diagnostico;
import model.Indicador;
import org.hibernate.Query;
import org.hibernate.Session;

public class DAOIndicador extends ADAO_crud<Object> implements Serializable{
    
    public Indicador getIndicadorByDiagnostico(Diagnostico diagnostico) {
        Indicador indicador = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from Indicador i inner join fetch i.muestras m inner join fetch m.ordenMedico om inner join fetch om.diagnostico d where d.codDiagnostico = :codDiagnostico");
            query.setParameter("codDiagnostico", diagnostico.getCodDiagnostico());
            indicador = (Indicador) query.uniqueResult();
            return indicador;
        } catch (Exception e) {
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return null;
    }
    
    public List<Indicador> getListIndicador(){
        List<Indicador> listIndicador = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from Indicador");
            listIndicador = (List<Indicador>) query.list();
        } catch (Exception e) {
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return listIndicador;
    }
}