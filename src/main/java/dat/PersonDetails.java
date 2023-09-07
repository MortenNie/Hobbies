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

    @Column(name= "zip",nullable = false)
    private int zip;



    @OneToOne
    @MapsId
    private Person person;

    @OneToOne(mappedBy = "personDetails",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Zipcode zipcode;

    public void addZipcode(Zipcode zipcode) {
        this.zipcode = zipcode;
        if (zipcode != null) {
            zipcode.setPersonDetails(this);

        }

    }


    public PersonDetails(String address, int zip) {
        this.address = address;
        this.zip = zip;
    }
}
