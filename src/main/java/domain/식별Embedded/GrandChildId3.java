package domain.식별Embedded;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class GrandChildId3 implements Serializable {

    private ChildId3 childId3;

    @Column(name = "GRANDCHILD_ID")
    private String id;

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}

