package embeddedtype;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
public class Address {

    @Getter
    private String street;

    @Getter
    private String city;

    @Getter
    private String state;

    public Address(String street, String city, String state) {
        this.street = street;
        this.city = city;
        this.state = state;
    }


    @Embedded
    private Zipcode zipcode;
}
