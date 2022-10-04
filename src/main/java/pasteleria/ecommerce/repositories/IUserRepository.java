package pasteleria.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pasteleria.ecommerce.entities.User;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {
}
