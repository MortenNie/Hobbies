package dat;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HobbyDAOTest {
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
    void retrieveAllHobby() {
        List<Hobby> allHobbies = new ArrayList<>();
        Hobby hobby2 = new Hobby("Online-trolling", "General", Hobby.Type.Inside);
        allHobbies.add(hobby2);
        List<Hobby> actual = hobbyDAO.retrieveAllHobby();
        assertEquals(allHobbies, actual);
    }

    @Test
    void retrieveAllInterests() {
        int i = 1;
        int y = hobbyDAO.retrieveAllInterests(1);
        assertEquals(i, y);
    }

    @Test
    void retrieveAllHobbiesPlusInterest() {
        HashMap<String, Integer> kage = new HashMap<>();
        kage.put("Online-trolling", 1);
        HashMap<String, Integer> donut = hobbyDAO.retrieveAllHobbiesPlusInterest();
        assertEquals(kage, donut);
    }

    @Test
    void retrieveHobbyForAssign() {
    Hobby hobby = new Hobby("Online-trolling", "General", Hobby.Type.Inside);
    Hobby actual = hobbyDAO.retrieveHobbyForAssign("Online-trolling");
    assertEquals(hobby, actual);

    }

    @Test
    void retrieveAllPersonsWithHobby() {

    }
}