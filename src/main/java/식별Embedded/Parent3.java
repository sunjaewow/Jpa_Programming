package 식별Embedded;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Parent3 {
    @Id
    @Column(name = "PARENT3_ID")
    private String id;

    private String name;
}
