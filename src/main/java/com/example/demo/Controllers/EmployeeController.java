package com.example.demo.Controllers;


import com.example.demo.models.Employee;
import com.example.demo.models.Menu;
import com.example.demo.models.UserClass;
import com.example.demo.repos.EmployeeRepos;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeRepos employeeRepos;

    public EmployeeController(EmployeeRepos employeeRepos) {
        this.employeeRepos = employeeRepos;
    }

    @GetMapping()
    public String employeeMain(Model model){
        Iterable<Employee> employees = employeeRepos.findAll();
        model.addAttribute("employees", employees);
        return "employees/index";
    }

    @GetMapping("/new")
    public String employeeAddPage(@ModelAttribute("employee") Employee employee,  Model model){
        model.addAttribute("employee", employee);
        return "employees/new";
    }

    @PostMapping("/new")
    public String employeeCreate(@ModelAttribute("employee") @Valid Employee employee,
                                 BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){return "/employees/new";}

        employeeRepos.save(employee);

        return "redirect:/employees";
    }

    @GetMapping("/{id}")
    public String showEmployee(@PathVariable(value = "id") Long id, Model model){
        if(!employeeRepos.existsById(id)){
            return "redirect:/employees";
        }


        Optional<Employee> employee = employeeRepos.findById(id);
        ArrayList<Employee> res = new ArrayList<>();
        employee.ifPresent(res::add);
        model.addAttribute("employee", res);
        return "employees/show";
    }

    @GetMapping("/{id}/edit")
    public String employeeEdit(@PathVariable(value = "id") Long id, Model model){
        if(!employeeRepos.existsById(id)){
            return "redirect:/employees";
        }
        Employee employee = employeeRepos.findById(id).orElseThrow();
        model.addAttribute("employee", employee);
        return "employees/edit";
    }

    @PostMapping("/{id}/edit")
    public String employeeUpdate(@PathVariable(value = "id") Long id,
                                 @ModelAttribute @Valid Employee employee, BindingResult bindingResult,Model model){
        if(bindingResult.hasErrors()){
            return "employees/edit";}
        employeeRepos.save(employee);
        return "redirect:/employees";
    }

    @PostMapping("/{id}/remove")
    public String employeesRemove(@PathVariable(value = "id") Long id,Model model){
        Employee employee = employeeRepos.findById(id).orElseThrow();

        employeeRepos.delete(employee);

        return "redirect:/employees";
    }
}
