package com.example.demo.Controllers;

import com.example.demo.models.Employee;
import com.example.demo.models.Ingredient;
import com.example.demo.models.UserClass;
import com.example.demo.repos.IngredientRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping("/ingredients")
public class IngredientController {
    @Autowired
    private IngredientRepository ingredientRepository; // Внедрение сервиса для управления данными

    @GetMapping()
    public String ingredientMain(Model model){
        Iterable<Ingredient> ingredients = ingredientRepository.findAll();
        model.addAttribute("ingredients", ingredients);
        return "ingredients/index";
    }

    @GetMapping("/new")
    public String ingredientAddPage(@ModelAttribute("ingredient") Ingredient ingredient, Model model){
        model.addAttribute("ingredient", ingredient);
        return "ingredients/new";
    }

    @PostMapping("/new")
    public String ingredientCreate(@ModelAttribute("ingredient") @Valid Ingredient ingredient,
                                 BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){return "/ingredients/new";}

        ingredientRepository.save(ingredient);

        return "redirect:/ingredients";
    }

    @GetMapping("/{id}")
    public String showIngredient(@PathVariable(value = "id") Long id, Model model){
        if(!ingredientRepository.existsById(id)){
            return "redirect:/ingredients";
        }
        Optional<Ingredient> ingredient = ingredientRepository.findById(id);
        ArrayList<Ingredient> res = new ArrayList<>();
        ingredient.ifPresent(res::add);
        model.addAttribute("ingredient", res);
        return "ingredients/show";
    }

    @GetMapping("/{id}/edit")
    public String ingredientEdit(@PathVariable(value = "id") Long id, Model model){
        //if(!userRepos.existsById(id)){return "redirect:/users";}
        Ingredient ingredient = ingredientRepository.findById(id).orElseThrow();
        model.addAttribute("ingredient", ingredient);
        return "ingredients/edit";
    }

    @PostMapping("/{id}/edit")
    public String ingredientUpdate(@ModelAttribute("ingredient") @Valid Ingredient ingredient,
                                   BindingResult bindingResult, @PathVariable(value = "id") Long id){
        if(bindingResult.hasErrors()){
            return "ingredients/edit";
        }
        ingredientRepository.save(ingredient);
        return "redirect:/ingredients";
    }


    @PostMapping("/{id}/remove")
    public String ingredientRemove(@PathVariable(value = "id") Long id,Model model){
        Ingredient ingredient = ingredientRepository.findById(id).orElseThrow();

        ingredientRepository.delete(ingredient);

        return "redirect:/ingredients";
    }
}
