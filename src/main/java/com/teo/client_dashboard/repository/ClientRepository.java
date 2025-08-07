package com.teo.client_dashboard.repository;

import com.teo.client_dashboard.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    
    List<Client> findByPrenomContainingIgnoreCaseOrNomContainingIgnoreCaseOrEntrepriseContainingIgnoreCase(
        String prenom, String nom, String entreprise);
    
    Page<Client> findByPrenomContainingIgnoreCaseOrNomContainingIgnoreCaseOrEntrepriseContainingIgnoreCase(
        String prenom, String nom, String entreprise,
        Pageable pageable
    );
}

