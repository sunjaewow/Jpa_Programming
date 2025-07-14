package domain.비식별Embedded;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable//좀 더 객체지향 적으로 설계 한 결과 기본생성자는 필수.
@NoArgsConstructor//hashcode 와 equals는 무조건 재정의해야함.
public class ParentId1 implements Serializable {

    @Column(name = "PARENT_ID1")
    private String id1;

    @Column(name = "PARENT_ID2")
    private String id2;

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
