package practice;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import practice.item.Address;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NamedQuery(name = "Member.findByName",
query = "select m from Member8 m where m.name=:name")//name쿼리 동적 쿼리보다 애플리케이션 로딩 시점에 JPQL 문법을 체크하고 미리 파싱해 둠
//오류를 빨리 확인할 수 있고, 사용하는 시점에는 파싱된 결과를 재사용하므로 성능상 이점도 있다. 데이터베이스의 조회 성능 최적화에도 도움이 된다.
public class Member8 extends  BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER1_ID")
    private Long id;

    private String email;

    private String name;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

}
