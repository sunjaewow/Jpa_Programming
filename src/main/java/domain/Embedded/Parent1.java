package domain.Embedded;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Parent1 {

    @EmbeddedId
    private ParentId1 parentId1;

    private String name;
}
