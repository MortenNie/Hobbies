package dat;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class HobbyDAO {

    EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();

    public List<Hobby> retrieveAllHobby() {


        try (var em = emf.createEntityManager()) {

            TypedQuery<Hobby> query = em.createQuery("SELECT h FROM Hobby h", Hobby.class);
            List<Hobby> results = query.getResultList();

            return results;

        }
    }
}





