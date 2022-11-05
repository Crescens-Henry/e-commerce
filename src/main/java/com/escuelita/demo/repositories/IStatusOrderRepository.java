package com.escuelita.demo.repositories;

import com.escuelita.demo.entities.StatusOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IStatusOrderRepository extends JpaRepository<StatusOrder, Long> {
}
