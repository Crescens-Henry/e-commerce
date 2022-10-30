package pasteleria.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pasteleria.ecommerce.entities.Client;

@Repository
public interface IClientRepository extends JpaRepository<Client, Long> {

}
