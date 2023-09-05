package dat;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
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
}
