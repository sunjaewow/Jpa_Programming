package domain.item;


import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)//상속 매핑 조인 전략
@DiscriminatorColumn(name = "DTYPE")//부모 클래스에 구분 칼럼을 지정. 기본값 DTYPE
public abstract class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ITEM_ID")
    private Long id;
}
