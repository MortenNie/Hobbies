package dat;

import jakarta.persistence.EntityManagerFactory;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Person gudrund = new Person("Gudrund", "Jensen", LocalDate.of(2023,9,4));
        PersonDetails gudrundsPersonalDetails = new PersonDetails("New Kagevej 7", "Kagerup", 8000);
        gudrund.addPersonalDetails(gudrundsPersonalDetails);
        Hobby hobby = new Hobby("Kage-making", "General", Hobby.Type.Inside);
        gudrund.addHobbies(hobby);
        Contact contact = new Contact("kage@hotmail.com","42424242");
        gudrund.addContacts(contact);

        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();
        try (var em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(gudrund);
            em.getTransaction().commit();
            

        }





    }
}