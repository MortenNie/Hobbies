package dat;

import jakarta.persistence.EntityManagerFactory;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

public class Main {
    public static void main(String[] args) {
                PersonDetailsDAO pdDao = new PersonDetailsDAO();
                pdDao.getAllInfoFromID(1);
        



       /* HobbyDAO dao = new HobbyDAO();
        Person sigrid = new Person("Steen", "The Destroyer", LocalDate.of(2023, 9, 5));
        PersonDetails sigridsPersonalDetails = new PersonDetails("New Vikingsvej 7", "Tarm", 6880);
        sigrid.addPersonalDetails(sigridsPersonalDetails);
        //Hobby hobby = new Hobby("Online-trolling", "General", Hobby.Type.Inside);
        Hobby hobby = dao.retrieveHobbyForAssign("Online-trolling");
        sigrid.addHobbies(hobby, sigrid);
        Contact contact = new Contact("Th3D3stroy3r@mail.com", "42424282");
        sigrid.addContacts(contact);

        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();
        try (var em = emf.createEntityManager()) {
            em.getTransaction().begin();
            //em.persist(hobby);
            em.persist(sigrid);
            em.getTransaction().commit();

        }

        HashMap<String, Integer> interestplushobbies = dao.retrieveAllHobbiesPlusInterest();
        System.out.println(interestplushobbies);
*/
    }
}
