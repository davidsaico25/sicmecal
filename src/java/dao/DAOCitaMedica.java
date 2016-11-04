package dao;

import java.io.Serializable;
import java.util.List;
import model.CitaMedica;
import model.Persona;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author davisonsp
 */
public class DAOCitaMedica extends ADAO_crud<Object> implements Serializable {
    
    public List<CitaMedica> getListCitaMedicaByPersona(Persona persona) {
        List<CitaMedica> listCitaMedica = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from CitaMedica cm inner join fetch cm.paciente p inner join fetch p.usuario up inner join fetch up.persona perp inner join fetch cm.turno t inner join fetch t.medico m inner join fetch m.usuario um inner join fetch um.persona perm where perp.numeroDocumento = :numeroDocumento and cm.estado = 'P'");//and cm.fecha <  TO_DATE('2016-11-04 08:00:00', 'YYYY-MM-DD HH24:MI:SS')
            query.setParameter("numeroDocumento", persona.getNumeroDocumento());
            listCitaMedica = (List<CitaMedica>) query.list();
        } catch (Exception e) {
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return listCitaMedica;
    }
}
