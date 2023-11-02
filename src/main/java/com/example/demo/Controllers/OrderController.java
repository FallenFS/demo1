package com.example.demo.Controllers;


import com.example.demo.models.Employee;
import com.example.demo.models.Orders;
import com.example.demo.models.UserClass;
import com.example.demo.repos.OrderRepos;
import jakarta.persistence.Access;
import jakarta.persistence.criteria.Order;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderRepos orderRepos;

    @GetMapping()
    public String orderMain(Model model){
        Iterable<Orders> orders = orderRepos.findAll();
        model.addAttribute("orders", orders);
        return "orders/index";
    }

    @GetMapping("/new")
    public String orderAddPage(@ModelAttribute("orders") Orders orders, Model model){
        model.addAttribute("orders", orders);
        return "orders/new";
    }

    @PostMapping("/new")
    public String orderCreate(@ModelAttribute("orders") @Valid Orders orders, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){

            return "orders/new";}
        orderRepos.save(orders);
        return "redirect:/orders";
    }

    @GetMapping("/{id}")
    public String showOrder(@PathVariable(value = "id") Long id, Model model){
        if(!orderRepos.existsById(id)){
            return "redirect:/orders";
        }


        Optional<Orders> orders = orderRepos.findById(id);
        ArrayList<Orders> res = new ArrayList<>();
        orders.ifPresent(res::add);
        model.addAttribute("orders", res);
        return "orders/show";
    }


    @GetMapping("/{id}/edit")
    public String orderEdit(@PathVariable(value = "id") Long id, Model model){
        if(!orderRepos.existsById(id)){
            return "redirect:/orders";
        }
        Orders orders = orderRepos.findById(id).orElseThrow();
        model.addAttribute("orders", orders);
        return "orders/edit";
    }

    @PostMapping("/{id}/edit")
    public String orderUpdate(@PathVariable(value = "id") Long id,
                              @ModelAttribute @Valid Orders orders, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            return "orders/edit";}
        orderRepos.save(orders);
        return "redirect:/orders";
    }

    @PostMapping("/{id}/remove")
    public String orderRemove(@PathVariable(value = "id") Long id,Model model){
        Orders orders = orderRepos.findById(id).orElseThrow();

        orderRepos.delete(orders);

        return "redirect:/orders";
    }
}
