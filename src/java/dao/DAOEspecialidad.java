package dao;

import java.io.Serializable;
import java.util.List;
import model.Especialidad;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author davisonsp
 */
public class DAOEspecialidad extends ADAO_crud<Object> implements Serializable {
    
    public List<Especialidad> getListEspecialidad() {
        List<Especialidad> listEspecialidad = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from Especialidad");
            listEspecialidad = (List<Especialidad>) query.list();
        } catch (Exception e) {
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return listEspecialidad;
    }
}
