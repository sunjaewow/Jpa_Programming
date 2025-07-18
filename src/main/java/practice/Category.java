package practice;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import practice.item.Item;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CATEGORY_ID")
    private Long id;

    @ManyToMany
    @JoinTable(name = "CATEGORY_ITEM",
            joinColumns = @JoinColumn(name = "CATEGORY_ID"),
            inverseJoinColumns = @JoinColumn(name = "ITEM_ID"))
    private List<Item> items = new ArrayList<>();

    private String name;

    public void addItem(Item item) {
        if (item.getCategories() != null) {
            item.getCategories().add(this);
        }
        items.add(item);
    }
}
