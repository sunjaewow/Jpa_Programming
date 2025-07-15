package embeddedtype;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;

@Embeddable
public class Address {

    private String street;

    private String city;

    private String state;

    @Embedded
    private Zipcode zipcode;
}
