package dao;

import java.io.Serializable;
import java.util.List;
import model.Especialidad;
import model.Medico;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author davisonsp
 */
public class DAOMedico extends ADAO_crud<Object> implements Serializable {
    
    public List<Medico> getListMedico() {
        List<Medico> listMedico = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from Medico m inner join fetch m.especialidad e inner join fetch m.usuario u inner join fetch u.persona p");
            listMedico = (List<Medico>) query.list();
        } catch (Exception e) {
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return listMedico;
    }
    
    public List<Medico> getListMedicoPerEspecialidad(Especialidad especialidad) {
        List<Medico> listMedico = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from Medico m inner join fetch m.especialidad e inner join fetch m.usuario u inner join fetch u.persona p where m.especialidad.codEspecialidad = :codEspecialidad");
            query.setParameter("codEspecialidad", especialidad.getCodEspecialidad());
            listMedico = (List<Medico>) query.list();
        } catch (Exception e) {
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return listMedico;
    }
}
