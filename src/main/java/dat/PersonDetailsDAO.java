package dat;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

import java.util.*;

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

            TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p JOIN PersonDetails c ON p.id = c.id JOIN Zipcode k ON c.zip = k.zip WHERE k.city_name = :parameter", Person.class);
            query.setParameter("parameter", cityName);
            List<Person> resultByCity = query.getResultList();

            return resultByCity;

        }

    }

    public Map<String, Integer> retrieveAllCitiesAndZips() {

        try (var em = emf.createEntityManager()) {
            Map<String, Integer> mapOfZipsAndCities = new HashMap<>();
            TypedQuery<Zipcode> query = em.createQuery("SELECT p FROM Zipcode p", Zipcode.class);
            List<Zipcode> citiesZips =query.getResultList();

            for (Zipcode s: citiesZips) {
                mapOfZipsAndCities.put(s.getCity_name(),s.getZip());


            }

            return mapOfZipsAndCities;
        }
    }
    public PersonDetails savePersonalDetails(PersonDetails personDetails)
    {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(personDetails);
        em.getTransaction().commit();
        em.close();
        return personDetails;
    }

    public PersonDetails findByIdPersonDetails(int id)
    {
        EntityManager em = emf.createEntityManager();
        PersonDetails foundPersonDetails = em.find(PersonDetails.class, id);
        em.close();
        return foundPersonDetails;
    }

    public PersonDetails updatePersonDetails(PersonDetails personDetails)
    {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        PersonDetails updatedPersonDetails = em.merge(personDetails);
        em.getTransaction().commit();
        em.close();
        return updatedPersonDetails;

    }

    public void deletePersonDetails(int id)
    {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        PersonDetails personDetails = findByIdPersonDetails(id);
        if (personDetails != null)
        {
            em.remove(personDetails);
        }
        em.getTransaction().commit();
        em.close();
    }



}

