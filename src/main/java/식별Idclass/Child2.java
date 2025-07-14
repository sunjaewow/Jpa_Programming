package 식별Idclass;

import jakarta.persistence.*;

@Entity
@IdClass(Child2Id.class)
public class Child2 {

    @Id
    @ManyToOne
    @JoinColumn(name = "PARENT_ID")
    private Parent2 parent2;

    @Id
    @Column(name = "CHILD_ID")
    private String childId;

    private String name;
}
