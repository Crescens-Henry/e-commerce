package pasteleria.ecommerce.repositories;

import javax.persistence.criteria.Order;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderRepository extends JpaRepository<Order, Long> {

}
