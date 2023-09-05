package dat;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = "person")
@Entity
public class PersonDetails {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "address",nullable = false)
    private String address;

    @Column (name = "city",nullable = false)
    private String city;

    @Column(name= "zip",nullable = false)
    private int zip;



    @OneToOne
    @MapsId
    private Person person;


    public PersonDetails(String address, String city, int zip) {
        this.address = address;
        this.city = city;
        this.zip = zip;
    }
}
