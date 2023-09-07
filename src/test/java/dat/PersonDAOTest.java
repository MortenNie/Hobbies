package dat;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PersonDAOTest {
    private static EntityManagerFactory emf;
    private static EntityManager em;
    private static PersonDetailsDAO pdDAO;

    private static HobbyDAO hobbyDAO;

    private static ContactDAO contactDAO;

    private static PersonDAO personDAO;

    @BeforeAll
    static void setUpALl() {
        emf = HibernateConfig.getEntityManagerFactoryConfig();
        em = emf.createEntityManager();
        pdDAO = new PersonDetailsDAO();
        contactDAO = new ContactDAO();
        personDAO = new PersonDAO();
        hobbyDAO = new HobbyDAO();



    }

    @AfterEach
    void tearDownAll() {
        em.close();

    }

    @Test
    void getPersonInfo() {

        //vi har allerede testet lignende metode i personDetails.


    }



    @Test
    void getPersonByTelephoneNumber() {

        Person p1 = new Person("Egon", "Olsen");
        Person actual = personDAO.getPersonByTelephoneNumber("55667789");
        assertNotEquals(p1, actual);




    }

    @Test
    void getPersonByNumber() {
    }
}