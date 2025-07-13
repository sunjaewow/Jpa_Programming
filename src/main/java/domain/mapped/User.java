package domain.mapped;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
@AttributeOverride(name = "id", column = @Column(name = "MEMBER_ID"))//부모에게 상속받은 id속성의 컬럼명을 변경
public class User extends BaseEntity{
    private String email;
}
