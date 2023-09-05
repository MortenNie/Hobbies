package dat;

import jakarta.persistence.EntityManagerFactory;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        HobbyDAO dao = new HobbyDAO();
        Person sigrid = new Person("Gudrund", "Olafsdottir", LocalDate.of(2023, 9, 5));
        PersonDetails sigridsPersonalDetails = new PersonDetails("New Vikingsvej 7", "Tarm", 6880);
        sigrid.addPersonalDetails(sigridsPersonalDetails);
        Hobby hobby = dao.retrieveHobbyForAssign("Kage-making");
        sigrid.addHobbies(hobby, sigrid);
        Contact contact = new Contact("kage@com", "42424242");
        sigrid.addContacts(contact);

        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();
        try (var em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(sigrid);
            em.getTransaction().commit();

        }

        HashMap<String, Integer> interestplushobbies = dao.retrieveAllHobbiesPlusInterest();
        System.out.println(interestplushobbies);

    }
}
