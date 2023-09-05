package dat;

import jakarta.persistence.EntityManagerFactory;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Person sigrid = new Person("Sigrid", "Olafsdottir", LocalDate.of(2023, 9, 5));
        PersonDetails sigridsPersonalDetails = new PersonDetails("New Vikingsvej 7", "Tarm", 6880);
        sigrid.addPersonalDetails(sigridsPersonalDetails);
        Hobby hobby = new Hobby("Kage-making", "General", Hobby.Type.Inside);
        sigrid.addHobbies(hobby, sigrid);
        Contact contact = new Contact("Sigridmail@mail.dk", "42424252");
        sigrid.addContacts(contact);

        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();
        try (var em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(hobby);
            em.persist(sigrid);
            em.getTransaction().commit();

        }
        HobbyDAO dao = new HobbyDAO();
        HashMap<String, Integer> interestplushobbies = dao.retrieveAllHobbiesPlusInterest();
        System.out.println(interestplushobbies);

    }
}
