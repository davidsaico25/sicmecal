package dao;

import java.io.Serializable;
import model.Paciente;
import model.Usuario;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author davisonsp
 */
public class DAOPaciente extends ADAO_crud<Object> implements Serializable {
    
    public Paciente getPaciente(Usuario usuario) {
        Paciente paciente = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from Paciente p inner join fetch p.usuario u inner join fetch u.persona where p.username = :username");
            query.setParameter("username", usuario.getUsername());
            paciente = (Paciente) query.uniqueResult();
            return paciente;
        } catch (Exception e) {
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return null;
    }
}
