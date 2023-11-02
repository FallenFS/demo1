package com.example.demo.Controllers;

import com.example.demo.models.Menu;
import com.example.demo.models.OrderedDishes;
import com.example.demo.models.Orders;
import com.example.demo.repos.MenuRepos;
import com.example.demo.repos.OrderRepos;
import com.example.demo.repos.OrderedDishesRepos;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/orderedDishes")
public class OrderedDishesController {
    @Autowired
    private OrderedDishesRepos orderedDishesRepository;

    @Autowired
    private OrderRepos orderRepository;

    @Autowired
    private MenuRepos dishRepository;

    @GetMapping("")
    public String listOrderedDishes(Model model) {
        List<OrderedDishes> orderedDishes = orderedDishesRepository.findAll();
        model.addAttribute("orderedDishes", orderedDishes);
        return "orderedDishes/index"; // Отображение списка заказанных блюд
    }

    @GetMapping("/new")
    public String createOrderedDishesForm(Model model) {
        List<Orders> orders = orderRepository.findAll();
        List<Menu> dishes = dishRepository.findAll();
        model.addAttribute("orders", orders);
        model.addAttribute("dishes", dishes);
        model.addAttribute("orderedDishes", new OrderedDishes());
        return "orderedDishes/new"; // Отображение формы создания заказанного блюда
    }

    @PostMapping("/new")
    public String createOrderedDishes(@ModelAttribute("orderedDishes") @Valid OrderedDishes orderedDishes,
                                      BindingResult bindingResult,
                                      RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "orderedDishes/new"; // Возврат на форму с ошибками валидации
        }

        // Ваши логика создания заказанного блюда
        orderedDishesRepository.save(orderedDishes);

        redirectAttributes.addFlashAttribute("successMessage", "Заказанное блюдо успешно создано");
        return "redirect:/orderedDishes"; // Перенаправление на список заказанных блюд
    }
}
