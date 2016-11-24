package dao;

import java.io.Serializable;
import java.util.List;
import model.Insumo;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author davisonsp
 */
public class DAOInsumo extends ADAO_crud<Object> implements Serializable {
    
    public List<Insumo> getListInsumo() {
        List<Insumo> listInsumo = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from Insumo");
            listInsumo = (List<Insumo>) query.list();
        } catch (Exception e) {
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return listInsumo;
    }
    
    public List<Insumo> getListInsumo1() {
        List<Insumo> listInsumo = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from Insumo i where i.cantidad<100");
            listInsumo = (List<Insumo>) query.list();
        } catch (Exception e) {
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return listInsumo;
    }
}
