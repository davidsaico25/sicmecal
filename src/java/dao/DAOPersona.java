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
            Query query = session.createQuery("from persona p inner join fetch p.paciente p inner join fetch "
                    + "p.usuario u inner join fetch u.persona per where p.nroDocumento = :nroDocumento");
            query.setParameter("nroDocumento", personaAux.getNumeroDocumento());
            persona = (Persona) query.uniqueResult();
            System.out.println("\\\\\\\\\\\\\\\\\\\\\\");
        }catch(Exception e){
        }finally{
            if(session != null){
                session.close();
            }
        }
        return persona;
    }
    
}
