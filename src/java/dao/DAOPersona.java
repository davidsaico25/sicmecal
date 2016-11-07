package dao;

import java.io.Serializable;
import model.Persona;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author davisonsp
 */
public class DAOPersona extends ADAO_crud<Object> implements Serializable {
    
    public Persona getPersonaByNroDoc(Persona personaAux){
        Persona persona = null;
        Session session = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from Persona p where p.numeroDocumento = :nroDocumento");
            query.setParameter("nroDocumento", personaAux.getNumeroDocumento());
            persona = (Persona) query.uniqueResult();
        }catch(Exception e){
        }finally{
            if(session != null){
                session.close();
            }
        }
        return persona;
    }
}
