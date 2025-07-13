package domain.mapped;

import jakarta.persistence.Entity;

@Entity
public class User extends BaseEntity{
    private String email;
}
