package dat;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ContactDAOTest {
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
    void retrieveAllNumbersFromUser() {
     List<String> listOfNumbers = new ArrayList<>();
     listOfNumbers.add("55667788");
     List<String> actual = contactDAO.retrieveAllNumbersFromUser(4);
     assertEquals(actual, listOfNumbers);

    }
}