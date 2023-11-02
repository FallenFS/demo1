package com.example.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @NotEmpty(message = "Имя не может быть пустым")
    @Size(min = 2, max = 30, message = "Имя должно быть в димпозоне от 2 до 30 символов")
    private String name;
    @NotEmpty(message = "Фамилия не может быть пустой")
    @Size(min = 2, max = 30, message = "Фамилия должна быть в димпозоне от 2 до 30 символов")
    private String secondname;
    @NotEmpty(message = "Отчество не может быть пустым")
    @Size(min = 2, max = 30, message = "Отчество должно быть в димпозоне от 2 до 30 символов")
    private String middlename;
    @NotEmpty(message = "Паспорт не может быть пустым")

    private String pasport;
    @NotEmpty(message = "Телефон не может быть пустым")

    private String telephone;
    @NotEmpty(message = "Почта не может быть пустым")
    @Email
    private String email;

    public Employee(){

    }

    public Employee(String name, String secondname, String middlename, String pasport, String telephone, String email) {
        this.name = name;
        this.secondname = secondname;
        this.middlename = middlename;
        this.pasport = pasport;
        this.telephone = telephone;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSecondname() {
        return secondname;
    }

    public void setSecondname(String secondname) {
        this.secondname = secondname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public String getPasport() {
        return pasport;
    }

    public void setPasport(String pasport) {
        this.pasport = pasport;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
