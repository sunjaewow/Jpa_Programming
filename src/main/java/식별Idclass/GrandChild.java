package 식별Idclass;

import domain.비식별Idclass.Child;
import jakarta.persistence.*;

@Entity
@IdClass(GrandChildId.class)
public class GrandChild {

    @Id
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "PARENT_ID"),
            @JoinColumn(name = "CHILD_ID")
    })
    private Child2 child2;

    @Id
    @Column(name = "GRANDCHILD_ID")
    private String id;

    private String name;
}
