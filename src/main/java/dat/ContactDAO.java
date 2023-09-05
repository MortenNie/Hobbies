package dat;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class ContactDAO {

    EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();


    public List<String> retrieveAllNumbersFromUser(Integer personId) {


        try (var em = emf.createEntityManager()) {

            TypedQuery<String> query = em.createQuery("SELECT c.telephoneNumber FROM Contact c WHERE c.person.id = :parameter",String.class);
            query.setParameter("parameter", personId);
            List<String> results = query.getResultList();

            return results;

        }
    }


}
