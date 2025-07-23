package repository;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.Specification;
import practice.Order;
import practice.OrderStatus;

import static org.springframework.data.jpa.domain.Specification.*;

@Getter
@Setter
public class OrderSearch {
    private String memberName;

    private OrderStatus orderStatus;

}
