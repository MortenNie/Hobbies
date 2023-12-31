package dat;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",unique = true, nullable = false)
    private int id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "user_created", nullable = false)
    @Temporal(TemporalType.DATE)
    private LocalDate userCreated;

    @OneToOne(mappedBy = "person", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private PersonDetails personDetails;

   /* @ManyToMany(mappedBy = "person", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Hobby> hobbies = new HashSet<>(); */

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<HobbyPerson> hobbies = new HashSet<>();




    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL,fetch = FetchType.EAGER )
    private Set<Contact> contacts = new HashSet<>();

    public Person(String firstName, String lastName ) {
        this.firstName = firstName;
        this.lastName = lastName;

    }

    public void addPersonalDetails(PersonDetails personDetails) {
        this.personDetails = personDetails;
        if (personDetails != null) {
            personDetails.setPerson(this);

        }

    }
    public void addHobbies(Hobby hobby, Person person) {
         HobbyPerson hobbyPerson = new HobbyPerson(hobby, person);
         this.hobbies.add(hobbyPerson);
    }

    public void addContacts(Contact contact) {
        this.contacts.add(contact);

        if (contacts != null) {
            contact.setPerson(this);

        }

    }

    @PrePersist
    public void onCreate() {
        this.userCreated = LocalDate.now();


    }

    @PreUpdate
    public void onUpdate() {
        this.userCreated = LocalDate.now();


    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(firstName, person.firstName) && Objects.equals(lastName, person.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }
}
