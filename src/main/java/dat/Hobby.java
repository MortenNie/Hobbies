package dat;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Fetch;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@ToString(exclude = {"person", "hobbyperson"})
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

    @OneToMany(mappedBy = "hobby", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hobby hobby = (Hobby) o;
        return Objects.equals(name, hobby.name) && Objects.equals(category, hobby.category) && type == hobby.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, category, type);
    }
}
