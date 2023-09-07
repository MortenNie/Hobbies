package dat;


import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PersonDetailsDAO {

    EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();


    public PersonDetails getAllPersonDetails(int personID) {
        try (var em = emf.createEntityManager()) {

            TypedQuery<PersonDetails> query = em.createQuery("SELECT pd FROM PersonDetails pd WHERE pd.person.id = :parameter", PersonDetails.class);
            query.setParameter("parameter", personID);
            PersonDetails pResults = query.getSingleResult();

            return pResults;
        }
    }

    public List<Contact> getAllContactInfo(int personID){

        try (var em = emf.createEntityManager()) {
            TypedQuery<Contact> query = em.createQuery("SELECT c FROM Contact c WHERE c.person.id = :parameter", Contact.class);
            query.setParameter("parameter", personID);
             List<Contact> cResults = query.getResultList();

            return cResults;
        }

    }


    public void getAllInfoFromID(int personID) {
        PersonDetailsDAO dao = new PersonDetailsDAO();
        PersonDAO ppDao = new PersonDAO();
        PersonDetails getDetails = dao.getAllPersonDetails(personID);
        List<Contact> getContact = dao.getAllContactInfo(personID);
        Person getPerson = ppDao.getPersonInfo(personID);


        System.out.println("Here's all the information:"+"\n" + getPerson + "\n" + getDetails +"\n"+ getContact);
    }


    public List<Person> retrieveAllPersonsByCity(String cityName) {

        try (var em = emf.createEntityManager()) {

            TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p JOIN p.personDetails c WHERE c.city = :parameter", Person.class);
            query.setParameter("parameter", cityName);
            List<Person> resultByCity = query.getResultList();

            return resultByCity;

        }

    }

    public List<String> retrieveAllCitiesAndZips() {

        try (var em = emf.createEntityManager()) {

            TypedQuery<String> query = em.createQuery("SELECT DISTINCT CONCAT (p.city,p.zip) FROM PersonDetails p", String.class);
            List<String> citiesZips =query.getResultList();

            return citiesZips;
        }
    }

}

