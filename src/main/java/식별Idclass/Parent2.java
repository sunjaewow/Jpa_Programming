package 식별Idclass;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Parent2 {
    @Id
    @Column(name = "PARENT_ID")
    private String id;

    private String name;
}
