package dat;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class ContactDAO {

    EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();


    public List<Contact> retrieveAllNumbersFromUser(int personId) {


        try (var em = emf.createEntityManager()) {

            TypedQuery<Contact> query = em.createQuery("SELECT c FROM Contact c WHERE c.person.id = :parameter",Contact.class);
            query.setParameter("parameter", personId);
            List<Contact> results = query.getResultList();

            return results;

        }
    }


}
