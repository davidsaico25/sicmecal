package dao;

import java.io.Serializable;
import model.Tecnologo;
import model.Usuario;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author davisonsp
 */
public class DAOTecnologo extends ADAO_crud<Object> implements Serializable {
    
    public Tecnologo getTecnologo(Usuario usuario) {
        Tecnologo tecnologo = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from Tecnologo p inner join fetch p.usuario u inner join fetch u.persona where p.username = :username");
            query.setParameter("username", usuario.getUsername());
            tecnologo = (Tecnologo) query.uniqueResult();
            return tecnologo;
        } catch (Exception e) {
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return null;
    }
}
