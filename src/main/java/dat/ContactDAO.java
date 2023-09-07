package dat;

import jakarta.persistence.EntityManager;
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

    public Contact saveContact(Contact contact)
    {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(contact);
        em.getTransaction().commit();
        em.close();
        return contact;
    }

    public Contact findByIdContact(int id)
    {
        EntityManager em = emf.createEntityManager();
        Contact foundContact = em.find(Contact.class, id);
        em.close();
        return foundContact;
    }

    public Contact updateContact(Contact contact)
    {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Contact updatedContact = em.merge(contact);
        em.getTransaction().commit();
        em.close();
        return updatedContact;

    }

    public void deleteContact(int id)
    {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Contact contact = findByIdContact(id);
        if (contact != null)
        {
            em.remove(contact);
        }
        em.getTransaction().commit();
        em.close();
    }

}
