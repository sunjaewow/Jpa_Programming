package embeddedtype;

import jakarta.persistence.*;

@Entity
public class PhoneServiceProvider {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PHONE_SERIVCE_PROVIDER")
    private Long id;


}
