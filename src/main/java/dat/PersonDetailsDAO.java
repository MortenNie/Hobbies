package dat;


import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

import java.util.HashMap;
import java.util.List;
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

}

