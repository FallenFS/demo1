package com.example.demo.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class Client {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Имя не может быть пустым")
    @Size(min = 2, max = 30, message = "Имя должно быть в димпозоне от 2 до 30 символов")
    private String name;
    @NotBlank(message = "Фамилия не может быть пустой")
    @Size(min = 2, max = 30, message = "Фамилия должна быть в димпозоне от 2 до 30 символов")
    private String secondname;
    @NotBlank(message = "Отчество не может быть пустым")
    @Size(min = 2, max = 30, message = "Отчество должно быть в димпозоне от 2 до 30 символов")
    private String middlename;
    @Size(min = 10, max = 12, message = "Номер телефона должен быть равен 11 символам")
    private String telephone;

    public Client(String name, String secondname, String middlename, String telephone) {
        this.name = name;
        this.secondname = secondname;
        this.middlename = middlename;
        this.telephone = telephone;
    }

    public Client() {
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

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
