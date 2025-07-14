package domain.식별Embedded;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class ChildId3 implements Serializable {
    private String parentId;

    @Column(name = "CHILD_ID")
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
