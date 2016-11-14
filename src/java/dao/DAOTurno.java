package dao;

import java.io.Serializable;
import java.util.List;
import model.Turno;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author davisonsp
 */
public class DAOTurno extends ADAO_crud<Object> implements Serializable {
    
    public List<Turno> getListTurno() {
        List<Turno> listTurno = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from Turno t inner join fetch t.medico m inner join fetch m.usuario u inner join fetch u.persona p inner join fetch m.especialidad e where t.estado = 'A' and t.numCitasMax > 0");
            listTurno = (List<Turno>) query.list();
        } catch (Exception e) {
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return listTurno;
    }
    
    public Turno getTurno(String codTurno) {
        Turno turno = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from Turno t where t.codTurno = " + codTurno);
            turno = (Turno) query.uniqueResult();
            return turno;
        } catch (Exception e) {
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return null;
    }
}
