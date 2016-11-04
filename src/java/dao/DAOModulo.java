package dao;

import java.io.Serializable;
import java.util.List;
import model.Modulo;
import model.Usuario;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author davisonsp
 */
public class DAOModulo extends ADAO_crud<Object> implements Serializable {
    
    public List<Modulo> getListModuloForUsuario(Usuario usuario) {
        List<Modulo> listModulo = null;
        Session session = null;
        try {//select DISTINCT(m) from Modulo m inner join fetch m.subModulos subm inner join fetch m.perfil p inner join fetch p.usuarios u where m.estado = 'A' and u.username =
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("select DISTINCT(m) from Modulo m inner join fetch m.subModulos subm inner join fetch m.perfil p inner join fetch p.usuarios u where m.estado = 'A' and subm.estado = 'A' and u.username = :username");
            query.setParameter("username", usuario.getUsername());
            listModulo = (List<Modulo>) query.list();
        } catch (Exception e) {
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return listModulo;
    }
}
