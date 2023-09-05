package dat;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

import java.util.HashMap;
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

    public Integer retrieveAllInterests(Integer hobbyId) {


        try (var em = emf.createEntityManager()) {

            Query query = em.createQuery("SELECT count(hp) FROM HobbyPerson hp WHERE hp.hobby.id = :parameter");
            query.setParameter("parameter", hobbyId);

            return (Integer) query.getSingleResult();


        }
    }

        public HashMap<String, Integer> retrieveAllHobbiesPlusInterest() {
            HashMap<String, Integer> hobbiesPlusInterest = new HashMap<>();
            HobbyDAO dao = new HobbyDAO();
            List<Hobby> hobbies = dao.retrieveAllHobby();

            for (Hobby s : hobbies) {

                Integer interestNumbers = retrieveAllInterests(s.getId());
                hobbiesPlusInterest.put(s.getName(),interestNumbers);

            }
           return hobbiesPlusInterest;
        }


}









