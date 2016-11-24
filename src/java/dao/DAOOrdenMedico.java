package dao;

import java.io.Serializable;
import java.util.List;
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
    
    public List<OrdenMedico> getListOrdenMedico() {
        List<OrdenMedico> listOrdenMedico = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from OrdenMedico om inner join fetch om.diagnostico d inner join fetch d.historialClinico hc inner join fetch hc.paciente p inner join fetch p.usuario u inner join fetch u.persona where om.estado = 'P'");
            listOrdenMedico = (List<OrdenMedico>) query.list();
        } catch (Exception e) {
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return listOrdenMedico;
    }
}
