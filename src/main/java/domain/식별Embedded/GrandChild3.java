package domain.식별Embedded;

import jakarta.persistence.*;

@Entity
public class GrandChild3 {

    @EmbeddedId
    private GrandChildId3 id;

    @MapsId("childId3")
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "PARENT_ID"),
            @JoinColumn(name = "CHILD_ID")
    })
    private Child3 child3;

    private String name;

}
