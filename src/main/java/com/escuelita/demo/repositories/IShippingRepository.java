package com.escuelita.demo.repositories;

import com.escuelita.demo.entities.Shipping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IShippingRepository extends JpaRepository<Shipping, Long> {
}
