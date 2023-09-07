package dat;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = "personDetails")
@Entity
public class Zipcode {

    @Id
    private int zip;
    private String city_name;
    private String region_name;
    private String municipality_name;


    @OneToOne
    private PersonDetails personDetails;

    public Zipcode(int zip, String city_name) {
        this.zip = zip;
        this.city_name = city_name;
    }
}
