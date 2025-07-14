package domain.비식별Embedded;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;

@Entity
public class Parent1 {

    @EmbeddedId
    private ParentId1 parentId1;

    private String name;
}
