package com.example.demo.Controllers;

import com.example.demo.models.UserClass;
import com.example.demo.repos.UserRepos;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserRepos userRepos;
    @Autowired
    public UserController(UserRepos userRepos) {
        this.userRepos = userRepos;
    }

    @GetMapping()
    public String userMain(Model model){
        Iterable<UserClass> users = userRepos.findAll();
        model.addAttribute("users", users);
        return "users/index";
    }

    @GetMapping("/new")
    public String userAddPage(@ModelAttribute("user") UserClass user,Model model){
        model.addAttribute("user", user);
        return "users/new";
    }

    @PostMapping("/new")
    public String userCreate(@ModelAttribute("user") @Valid UserClass user, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){

            return "users/new";}
        userRepos.save(user);
        return "redirect:/users";
    }

    @GetMapping("/{id}")
    public String showUser(@PathVariable(value = "id") Long id, Model model){
        if(!userRepos.existsById(id)){
            return "redirect:/users";
        }
        Optional<UserClass> user = userRepos.findById(id);
        ArrayList<UserClass> res = new ArrayList<>();
        user.ifPresent(res::add);
        model.addAttribute("user", res);
        return "users/show";
    }

    @GetMapping("/{id}/edit")
    public String userEdit(@PathVariable(value = "id") Long id, Model model){
        //if(!userRepos.existsById(id)){return "redirect:/users";}
        UserClass user = userRepos.findById(id).orElseThrow();
        model.addAttribute("user", user);
        return "users/edit";
    }

    @PostMapping("/{id}/edit")
    public String userUpdate(@ModelAttribute("user") @Valid UserClass user, BindingResult bindingResult,
                             @PathVariable(value = "id") Long id){
        if(bindingResult.hasErrors()){
            return "users/edit";
        }
        userRepos.save(user);
        return "redirect:/users";
    }

    @PostMapping("/{id}/remove")
    public String userRemove(@PathVariable(value = "id") Long id,Model model){
        UserClass user = userRepos.findById(id).orElseThrow();
        userRepos.delete(user);
        return "redirect:/users";
    }
}
