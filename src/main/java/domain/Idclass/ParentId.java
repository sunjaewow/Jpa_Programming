package domain.Idclass;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor//객체지향적이라니라 데이터베이스에 맞춘 방법임
//Parent가 persist를 하면 영속성 컨텍스트에 엔티티를 등록하기 전에 Parent id1, id2값을 받아 ParentId를 생성후 키로 사용함.
public class ParentId implements Serializable {

    private String id1;
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
