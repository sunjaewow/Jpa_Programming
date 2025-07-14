package practice.item;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import practice.Category;

import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "DYTPE")
@Getter
@Setter
public abstract class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ITEM_ID")
    private Long id;

    private String name;

    private int price;

    private int stockQuantity;  //재고수량

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();
}
