package com.example.demo.Controllers;

import com.example.demo.models.Client;
import com.example.demo.models.Orders;
import com.example.demo.models.Reservations;
import com.example.demo.repos.ClientRepos;
import com.example.demo.repos.ReservationRepos;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/reservations")
public class ReservationController {
    @Autowired
    private ReservationRepos reservationRepos;

    @Autowired
    private ClientRepos clientRepos;

    @GetMapping()
    public String reservationMain(Model model){
        Iterable<Reservations> reservations = reservationRepos.findAll();
        model.addAttribute("reservations", reservations);
        return "reservations/index";
    }

    @GetMapping("/new")
    public String createReservationForm(Model model) {
        List<Client> clients = clientRepos.findAll();
        model.addAttribute("clients", clients);
        model.addAttribute("reservations", new Reservations());
        return "reservations/new"; // Отображение формы создания резервации
    }

    @PostMapping("/new")
    public String createReservation(@ModelAttribute("reservations") @Valid Reservations reservations,
                                    BindingResult bindingResult,
                                    RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "reservations/new"; // Возврат на форму с ошибками валидации
        }

        // Ваши логика создания резервации
        reservationRepos.save(reservations);

        redirectAttributes.addFlashAttribute("successMessage", "Резервация успешно создана");
        return "redirect:/reservations"; // Перенаправление на список резерваций
    }

    @GetMapping("/{id}")
    public String viewReservation(@PathVariable Long id, Model model) {
        Reservations reservation = reservationRepos.findById(id).orElse(null);
        if (reservation == null) {
            // Обработка случая, если резервация не найдена
            return "redirect:/reservations"; // Или другое действие по вашему усмотрению
        }

        model.addAttribute("reservation", reservation);
        return "reservations/show"; // Отображение страницы с деталями резервации
    }


    @GetMapping("/{id}/edit")
    public String editReservationForm(@PathVariable Long id, Model model) {
        Reservations reservation = reservationRepos.findById(id).orElse(null);
        List<Client> customers = clientRepos.findAll();
        model.addAttribute("reservation", reservation);
        model.addAttribute("customers", customers);
        return "reservations/edit"; // Отображение формы редактирования резервации
    }

    @PostMapping("/{id}/edit")
    public String editReservation(@PathVariable Long id,
                                  @ModelAttribute("reservation") @Valid Reservations reservation,
                                  BindingResult bindingResult,
                                  RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "reservations/edit"; // Возврат на форму с ошибками валидации
        }

        // Ваша логика обновления резервации
        reservationRepos.save(reservation);

        redirectAttributes.addFlashAttribute("successMessage", "Резервация успешно обновлена");
        return "redirect:/reservations"; // Перенаправление на список резерваций
    }

    @PostMapping("/{id}/remove")
    public String deleteReservation(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        // Ваша логика удаления резервации
        reservationRepos.deleteById(id);

        redirectAttributes.addFlashAttribute("successMessage", "Резервация успешно удалена");
        return "redirect:/reservations"; // Перенаправление на список резерваций
    }
}
