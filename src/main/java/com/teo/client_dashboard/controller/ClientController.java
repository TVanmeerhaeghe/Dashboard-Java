package com.teo.client_dashboard.controller;

import com.teo.client_dashboard.model.Client;
import com.teo.client_dashboard.repository.ClientRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class ClientController {

    private final ClientRepository clientRepository;

    public ClientController(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @GetMapping("/")
    public String afficherClients(Model model) {
        List<Client> clients = clientRepository.findAll();
        System.out.println("DEBUG: trouvé " + clients.size() + " clients");
        clients.forEach(c -> System.out.println(" → " + c.getPrenom() + " " + c.getNom()));
        model.addAttribute("clients", clients);
        return "index";
    }

    @GetMapping("/ajouter")
    public String afficherFormulaire(Model model) {
        model.addAttribute("client", new Client());
        return "form";
    }

    @PostMapping("/ajouter")
    public String enregistrerClient(@ModelAttribute Client client) {
        client.setDateCreation(LocalDateTime.now());
        clientRepository.save(client);
        return "redirect:/"; 
    }   
}
