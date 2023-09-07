package dat;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

@Getter
@Setter
@ToString(exclude = "person")
@NoArgsConstructor
@Table(name = "contact")
@Entity
public class Contact {
    @Id
    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "telephone-number", nullable = false)
    private String telephoneNumber;

    @ManyToOne
    private Person person;

    public Contact(String email, String telephoneNumber) {
        this.email = email;
        this.telephoneNumber = telephoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return Objects.equals(email, contact.email) && Objects.equals(telephoneNumber, contact.telephoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, telephoneNumber);
    }
}
