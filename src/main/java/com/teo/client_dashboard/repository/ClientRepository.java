package com.teo.client_dashboard.repository;

import com.teo.client_dashboard.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    
}

