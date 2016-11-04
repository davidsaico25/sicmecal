package dao;

import java.io.Serializable;
import java.util.List;
import model.Indicador;
import org.hibernate.Query;
import org.hibernate.Session;

public class DAOIndicador extends ADAO_crud<Object> implements Serializable{
    
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
