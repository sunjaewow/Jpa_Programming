package embeddedtype;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER4_ID")
    private Long id;

    @Embedded
    private Address address;

    //만약 주소하나를 더 추가하고싶으면 prviate Address homeAddress? -> 매핑하는 칼럼명이 중복이 됨 그래서 속성 재정의를 해야함.

//    @Embedded
//    @AttributeOverrides({
//            @AttributeOverride(name = "city", column = @Column(name = "COMPANY_CITY")),
//            @AttributeOverride(name = "street", column = @Column(name = "COMPANY_STREET")),
//            @AttributeOverride(name = "state", column = @Column(name = "COMPANY_STATE"))
//    })
//    private Address companyAddress;//사용하면 어노테이션을 너무 많이 사용해서 지저분해짐.

    @ElementCollection
    @CollectionTable(name = "FAVORITE_FOODS",
            joinColumns = @JoinColumn(name = "MEMBER4_ID"))
    @Column(name = "FOOD_NAME")
    private Set<String> favoriteFoods = new HashSet<>();

//    @ElementCollection
//    @CollectionTable(name = "ADDRESS",
//            joinColumns = @JoinColumn(name = "MEMBER4_ID"))
//    private List<Address> addressHistory = new ArrayList<>(); 조회가 빈번하게 일어나는 경우 일대다로 하는게 훨씬 좋음

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "MEMBER4_ID")
    private List<AddressEntity> addressHistory = new ArrayList<>();
}
