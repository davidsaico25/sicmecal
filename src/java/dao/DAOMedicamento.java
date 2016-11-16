package dao;

import java.io.Serializable;
import java.util.List;
import model.Medicamento;
import org.hibernate.Query;
import org.hibernate.Session;

public class DAOMedicamento extends ADAO_crud<Object> implements Serializable{
    
    public List<Medicamento> getListMedicamento(){
        List<Medicamento> listMedicamento = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from Medicamento");
            listMedicamento = (List<Medicamento>) query.list();
        } catch (Exception e) {
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return listMedicamento;
    }
    
}
