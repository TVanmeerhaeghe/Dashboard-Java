package com.teo.client_dashboard.repository;

import com.teo.client_dashboard.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    
    List<Client> findByPrenomContainingIgnoreCaseOrNomContainingIgnoreCaseOrEntrepriseContainingIgnoreCase(
        String prenom, String nom, String entreprise);
}

