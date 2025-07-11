package domain;

import jakarta.persistence.*;
import practice.Member;

@Entity
@IdClass(MemberProductId.class)//복합 키를 사용하려면 식별자 클래스가 필요함.
public class MemberProduct {

    @Id
    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @Id
    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

    private int orderAmount;
}
