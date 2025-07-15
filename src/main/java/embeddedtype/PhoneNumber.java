package embeddedtype;

import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;

@Embeddable
public class PhoneNumber {

    private String areaCode;

    private String localNumber;

    @ManyToOne
    private PhoneServiceProvider phoneServiceProvider;
}
