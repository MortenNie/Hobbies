package dat;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@NoArgsConstructor
@ToString
public class HobbyPerson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private Hobby hobby;

    @ManyToOne
    private Person person;

    public HobbyPerson(Hobby hobby, Person person) {
        this.hobby = hobby;
        this.person = person;
    }
}
