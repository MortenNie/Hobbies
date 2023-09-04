package dat;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
@Table(name = "contact")
@Entity
public class Contact {
    @Id
    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "telephone-number", unique = true, nullable = false)
    private String telephoneNumber;

    @ManyToOne
    private Person person;

    public Contact(String email, String telephoneNumber) {
        this.email = email;
        this.telephoneNumber = telephoneNumber;
    }
}
