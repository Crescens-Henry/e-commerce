package pasteleria.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pasteleria.ecommerce.entities.Bill;

@Repository
public interface IBillRepository extends JpaRepository<Bill, Long> {

}
