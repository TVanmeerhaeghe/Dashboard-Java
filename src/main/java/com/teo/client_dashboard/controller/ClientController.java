package com.teo.client_dashboard.controller;

import com.teo.client_dashboard.model.Client;
import com.teo.client_dashboard.repository.ClientRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
        model.addAttribute("clients", clients);
        return "index";
    }

    @GetMapping("/ajouter")
    public String afficherFormulaire(Model model) {
        model.addAttribute("client", new Client());
        return "form";
    }

    @PostMapping("/ajouter")
    public String enregistrerClient(
            @Valid @ModelAttribute("client") Client client,
            BindingResult result,
            Model model
    ) {
        if (result.hasErrors()) {
            return "form";
        }
        client.setDateCreation(LocalDateTime.now());
        clientRepository.save(client);
        return "redirect:/";
    }

     @GetMapping("/modifier/{id}")
    public String afficherFormulaireModifier(
            @PathVariable Long id,
            Model model,
            RedirectAttributes ra
    ) {
        Client client = clientRepository.findById(id)
            .orElse(null);
        if (client == null) {
            ra.addFlashAttribute("danger", "Client introuvable (id=" + id + ")");
            return "redirect:/";
        }
        model.addAttribute("client", client);
        return "form";
    }

    @PostMapping("/modifier/{id}")
    public String mettreAJourClient(
            @PathVariable Long id,
            @Valid @ModelAttribute("client") Client client,
            BindingResult result,
            RedirectAttributes ra
    ) {
        if (result.hasErrors()) {
            return "form";
        }
        client.setId(id);
        clientRepository.save(client);
        ra.addFlashAttribute("success", "Client mis à jour avec succès !");
        return "redirect:/";
    }

    @PostMapping("/supprimer/{id}")
    public String supprimerClient(
            @PathVariable Long id,
            RedirectAttributes ra
    ) {
        Client client = clientRepository.findById(id)
            .orElse(null);
        if (client != null) {
            clientRepository.delete(client);
            ra.addFlashAttribute("success", "Client supprimé avec succès !");
        } else {
            ra.addFlashAttribute("danger", "Client introuvable (id=" + id + ")");
        }
        return "redirect:/";
    }
}
