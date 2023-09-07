package dat;

import jakarta.persistence.EntityManager;
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

            Query query = em.createQuery("SELECT CAST(count(hp) AS integer) FROM HobbyPerson hp WHERE hp.hobby.id = :parameter");
            query.setParameter("parameter", hobbyId);

            return (int) query.getSingleResult();


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

    public Hobby retrieveHobbyForAssign(String hobbyName) {


        try (var em = emf.createEntityManager()) {

            TypedQuery<Hobby> query = em.createQuery("SELECT h FROM Hobby h WHERE h.name = :parameter", Hobby.class);
            query.setParameter("parameter", hobbyName);
            Hobby result = query.getSingleResult();

            return result;

        }



    }

    public List<Person> retrieveAllPersonsWithHobby(String hobbyName) {

        try (var em = emf.createEntityManager()) {

            TypedQuery<Person> query = em.createQuery("SELECT ph FROM Person ph JOIN HobbyPerson e ON ph.id = e.id JOIN Hobby c ON e.id = c.id WHERE c.name = :parameter", Person.class);
            query.setParameter("parameter", hobbyName);
            List<Person> result = query.getResultList();

            return result;

        }



    }

    public Hobby saveHobby(Hobby hobby)
    {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(hobby);
        em.getTransaction().commit();
        em.close();
        return hobby;
    }

    public Hobby findByIdHobby(int id)
    {
        EntityManager em = emf.createEntityManager();
        Hobby foundHobby = em.find(Hobby.class, id);
        em.close();
        return foundHobby;
    }

    public Hobby updateHobby(Hobby hobby)
    {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Hobby updatedHobby = em.merge(hobby);
        em.getTransaction().commit();
        em.close();
        return updatedHobby;

    }

    public void deleteHobby(int id)
    {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Hobby hobby = findByIdHobby(id);
        if (hobby != null)
        {
            em.remove(hobby);
        }
        em.getTransaction().commit();
        em.close();
    }






}









