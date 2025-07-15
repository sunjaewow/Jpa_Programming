package embeddedtype;

import jakarta.persistence.Embeddable;

@Embeddable
public class Zipcode {

    private String zip;

    private String plusFour;
}
