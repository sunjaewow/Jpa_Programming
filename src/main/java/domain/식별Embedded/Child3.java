package domain.식별Embedded;

import jakarta.persistence.*;

@Entity
public class Child3 {

    @EmbeddedId
    private ChildId3 id3;

    @MapsId("parentId")
    @ManyToOne
    @JoinColumn(name = "PARENT_ID")
    private Parent3 parent3;

    private String name;
}
