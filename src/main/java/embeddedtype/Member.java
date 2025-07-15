package embeddedtype;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;

@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER4_ID")
    private Long id;

    @Embedded
    private Address address;

}
