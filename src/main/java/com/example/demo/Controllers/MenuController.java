package com.example.demo.Controllers;

import com.example.demo.models.Employee;
import com.example.demo.models.Menu;
import com.example.demo.models.UserClass;
import com.example.demo.repos.MenuRepos;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping("/menus")
public class MenuController {
    private final MenuRepos menuRepos;

    public MenuController(MenuRepos menuRepos) {
        this.menuRepos = menuRepos;
    }

    @GetMapping()
    public String menuMain(Model model){
        Iterable<Menu> menus = menuRepos.findAll();
        model.addAttribute("menus", menus);
        return "menus/index";
    }

    @GetMapping("/new")
    public String menuAddPage(@ModelAttribute("menu") Menu menu, Model model){
        model.addAttribute("menu", menu);
        return "menus/new";
    }

    @PostMapping("/new")
    public String menuCreate(@ModelAttribute("menu") @Valid Menu menu, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){return "/menus/new";}

        menuRepos.save(menu);
        return "redirect:/menus";
    }

    @GetMapping("/{id}")
    public String showMenu(@PathVariable(value = "id") Long id, Model model){
        if(!menuRepos.existsById(id)){
            return "redirect:/menus";
        }


        Optional<Menu> menu = menuRepos.findById(id);
        ArrayList<Menu> res = new ArrayList<>();
        menu.ifPresent(res::add);
        model.addAttribute("menu", res);
        return "menus/show";
    }

    @GetMapping("/{id}/edit")
    public String menuEdit( @PathVariable(value = "id") Long id, Model model){
        if(!menuRepos.existsById(id)){
            return "redirect:/menus";
        }
        Menu menu    = menuRepos.findById(id).orElseThrow();
        model.addAttribute("menu", menu);
        return "menus/edit";
    }

    @PostMapping("/{id}/edit")
    public String userUpdate(@PathVariable(value = "id") Long id, @ModelAttribute("menu") @Valid Menu menu,
                             BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return "menus/edit";
        menuRepos.save(menu);
        return "redirect:/menus";
    }

    @PostMapping("/{id}/remove")
    public String menuRemove(@PathVariable(value = "id") Long id,Model model){
        Menu user = menuRepos.findById(id).orElseThrow();

        menuRepos.delete(user);

        return "redirect:/menus";
    }
}
