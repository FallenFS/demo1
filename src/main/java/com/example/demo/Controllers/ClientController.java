package com.example.demo.Controllers;

import com.example.demo.models.Client;
import com.example.demo.models.Employee;
import com.example.demo.repos.ClientRepos;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;
import java.util.function.BiConsumer;

@Controller
@RequestMapping("/clients")
public class ClientController {
    private final ClientRepos clientRepos;

    public ClientController(ClientRepos clientRepos) {
        this.clientRepos = clientRepos;
    }

    @GetMapping()
    public String clientMain(Model model){
        Iterable<Client> clients = clientRepos.findAll();
        model.addAttribute("clients", clients);
        return "clients/index";
    }

    @GetMapping("/new")
    public String clientAddPage(@ModelAttribute("client") Client client, Model model){
        model.addAttribute("client", client);
        return "clients/new";
    }

    @PostMapping("/new")
    public String clientCreate(@ModelAttribute("client") @Valid Client client, BindingResult bindingResult,
                                  Model model){
        if(bindingResult.hasErrors()){return "/clients/new";}

        clientRepos.save(client);

        return "redirect:/clients";
    }

    @GetMapping("/{id}")
    public String showClient(@PathVariable(value = "id") Long id, Model model){
        if(!clientRepos.existsById(id)){
            return "redirect:/clients";
        }


        Optional<Client> client = clientRepos.findById(id);
        ArrayList<Client> res = new ArrayList<>();
        client.ifPresent(res::add);
        model.addAttribute("client", res);
        return "clients/show";
    }

    @GetMapping("/{id}/edit")
    public String clientEdit(@PathVariable(value = "id") Long id, Model model){
        if(!clientRepos.existsById(id)){
            return "redirect:/clients";
        }
        Client client = clientRepos.findById(id).orElseThrow();
        model.addAttribute("client", client);
        return "clients/edit";
    }

    @PostMapping("/{id}/edit")
    public String clientUpdate(@ModelAttribute("client") @Valid Client client,
                               @PathVariable(value = "id") Long id, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "clients/edit";
        }
        clientRepos.save(client);
        return "redirect:/clients";
    }

    @PostMapping("/{id}/remove")
    public String clientRemove(@PathVariable(value = "id") Long id,Model model){
        Client client = clientRepos.findById(id).orElseThrow();

        clientRepos.delete(client);

        return "redirect:/clients";
    }
}
