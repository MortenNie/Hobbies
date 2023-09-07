package dat;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PersonDetailsDAOTest {
    private static EntityManagerFactory emf;
    private static EntityManager em;
    private static PersonDetailsDAO pdDAO;

    private static HobbyDAO hobbyDAO;

    private static ContactDAO contactDAO;

    private static PersonDAO personDAO;


    private static List<PersonDetails> list;

    @BeforeAll
    static void setUpALl() {
        emf = HibernateConfig.getEntityManagerFactoryConfig();
        em = emf.createEntityManager();
        pdDAO = new PersonDetailsDAO();
        contactDAO = new ContactDAO();
        personDAO = new PersonDAO();
        hobbyDAO = new HobbyDAO();
        list = new ArrayList<>();


    }

    @AfterEach
    void tearDownAll() {
        em.close();

    }

    @Test
    void getAllPersonDetails() {

        PersonDetails pd1 = pdDAO.getAllPersonDetails(4);
        PersonDetails guesspd2 = new PersonDetails("Kagevej 7", 2800);
        assertEquals(pd1, guesspd2);


    }

    @Test
    void getAllContactInfo() {
        List<Contact> contactInfo = new ArrayList<>();
        Contact contact = new Contact("kage@com","55667788");
        contactInfo.add(contact);
        List<Contact> actual = pdDAO.getAllContactInfo(4);
        assertEquals(contactInfo, actual);
        
        
        
    }

    @Test
    void getAllInfoFromID() {

        


    }

    @Test
    void retrieveAllPersonsByCity() {
    }

    @Test
    void retrieveAllCitiesAndZips() {
    }

    private static void createPeople() {
        em.getTransaction().begin();
        PersonDAO personDAO = new PersonDAO();
        HobbyDAO hobbyDAO = new HobbyDAO();
        Person p1 = new Person("Egon", "Olsen");
        Person p2 = new Person("Gudrund", "Jensen");
        PersonDetails pd1 = new PersonDetails("Kagevej 7", 2800);
        PersonDetails pd2 = new PersonDetails("Kagevej 8", 2800);
        Hobby hobby = new Hobby("Kage-making", "General", Hobby.Type.Inside);
        Contact contact1 = new Contact("kage3@com", "55667788");
        Contact contact2 = new Contact("kage4@com", "55667789");

        p1.addHobbies(hobby, p1);
        p2.addHobbies(hobby, p2);
        p1.addPersonalDetails(pd1);
        p2.addPersonalDetails(pd2);
        p1.addContacts(contact1);
        p2.addContacts(contact2);
        em.persist(hobby);
        em.persist(p1);
        em.persist(p2);
        em.getTransaction().commit();

    }



    public static void deleteALlPersons() {


        try(EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Query query = em.createQuery("DELETE FROM Person");
            query.executeUpdate();
            em.getTransaction().commit();
        }




    }

    public static void  deleteTHEFUCKINGHOBBY(int hobbyId) {
        em.getTransaction().begin();
        Hobby hobby = em.find(Hobby.class, hobbyId);
        em.remove(hobby);
        em.getTransaction().commit();



    }


    }
