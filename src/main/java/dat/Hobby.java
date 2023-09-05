package dat;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Fetch;

import java.util.HashSet;
import java.util.Set;

@Getter
@ToString
@NoArgsConstructor
@Table(name = "hobby")
@Entity
public class Hobby {
    //id name category type
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private int id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "category", nullable = false)
    private String category;

    /*@ManyToMany(fetch = FetchType.EAGER)
    private Set<Person> person; */

    @OneToMany(mappedBy = "hobby", cascade = CascadeType.ALL)
    private Set<HobbyPerson> persons= new HashSet<>();

    @Enumerated(EnumType.STRING)
    private Type type;




    public Hobby(String name, String category, Type type) {
        this.name = name;
        this.category = category;
        this.type = type;
    }


    public enum Type {
        Inside, Outside, Competition, Observation
    }
}
