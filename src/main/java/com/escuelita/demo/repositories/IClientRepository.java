package com.escuelita.demo.repositories;

import com.escuelita.demo.entities.Client;
import com.escuelita.demo.entities.projections.ClientProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IClientRepository extends JpaRepository<Client, Long> {
    @Query(value = "select * from clients where clients.email = :email", nativeQuery = true)
    List<ClientProjection> findClientByEmail(@Param("email") String email);

    Optional<Client> findOneByEmail(String email);

}
