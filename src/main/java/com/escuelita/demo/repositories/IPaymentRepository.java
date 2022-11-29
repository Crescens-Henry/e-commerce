package com.escuelita.demo.repositories;

import com.escuelita.demo.entities.Payment;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IPaymentRepository extends JpaRepository<Payment, Long> {
    @Query(value = "SELECT  payments.* from payments " +
            "where client_id = :clientId", nativeQuery = true)
    List<Payment> listAllPaymentsByClientId(@Param("clientId") Long clientId);
}
