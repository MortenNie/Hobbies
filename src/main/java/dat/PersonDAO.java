package dat;
import jakarta.persistence.EntityManager;
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

    public Person getPersonByTelephoneNumber(String telephoneNumber) {
        try (var em = emf.createEntityManager()) {
            TypedQuery<Person> query = em.createQuery("SELECT c.person FROM Contact c WHERE c.telephoneNumber = :parameter", Person.class);
            query.setParameter("parameter", telephoneNumber);
            Person personsNumberResults = query.getSingleResult();

            return personsNumberResults;
        }
    }
    public void getPersonByNumber(String telephoneNumber) {
        PersonDAO ppdao = new PersonDAO();
        Person getNumber = ppdao.getPersonByTelephoneNumber(telephoneNumber);

        System.out.println(getNumber);


    }
    public Person savePerson(Person person)
    {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(person);
        em.getTransaction().commit();
        em.close();
        return person;
    }

    public Person findByIdPerson(int id)
    {
        EntityManager em = emf.createEntityManager();
        Person foundPerson = em.find(Person.class, id);
        em.close();
        return foundPerson;
    }

    public Person updatePerson(Person person)
    {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Person updatedPerson = em.merge(person);
        em.getTransaction().commit();
        em.close();
        return updatedPerson;

    }

    public void deletePerson(int id)
    {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Person person = findByIdPerson(id);
        if (person != null)
        {
            em.remove(person);
        }
        em.getTransaction().commit();
        em.close();
    }


}
