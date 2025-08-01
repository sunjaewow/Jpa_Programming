package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import practice.Order;

public interface OrderRepository extends JpaRepository<Order, Long> , JpaSpecificationExecutor<Order> {

}
