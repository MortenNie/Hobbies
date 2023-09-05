package dat;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

import java.util.HashMap;
import java.util.List;
public class PersonDAO {

    EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();

    public Person getPersonInfo(int personID) {
        try (var em = emf.createEntityManager()) {

            TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p WHERE p.id = :parameter", Person.class);
            query.setParameter("parameter", personID);
            Person personResults = query.getSingleResult();

            return personResults;

        }

    }
}
