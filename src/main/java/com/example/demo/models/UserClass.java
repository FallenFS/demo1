package com.example.demo.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.UniqueElements;

@Entity
public class UserClass {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "Логин не может быть пустым")
    @Size(min = 2, max = 50, message = "Логин должно быть в димпозоне от 2 до 50 символов")
    private String login;
    @NotBlank(message = "Пароль не может быть пустым")
    @Min(value = 7, message = "Пароль должно быть больше 7 символов")
    private String password;
    @NotBlank(message = "Телефон не может быть пустым")
    @Size(min = 10,max = 12,message = "Номер телефона должен состоять из 11 символов")
    private String telephone;
    @NotBlank(message = "Почта не может быть пустым")
    @Email
    private String email;

    public UserClass() {
    }

    public UserClass(String login, String password, String telephone, String email) {
        this.login = login;
        this.password = password;
        this.telephone = telephone;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
