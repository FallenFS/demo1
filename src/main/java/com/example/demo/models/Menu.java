package com.example.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;

@Entity
public class Menu {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "Название блюда не может быть пустым")
    @Size(max = 255, message = "Название блюда не должно превышать 255 символов")
    private String name;
    @Size(max = 1000, message = "Описание блюда не должно превышать 1000 символов")

    private String description;
    //@NotEmpty(message = "Укажтите цену блюда")
    @NotNull(message = "Цена блюда не может быть пустой")
    @Positive(message = "Цена блюда должна быть положительной")
    private Integer price;
    @Size(max = 255, message = "Название категории не должно превышать 255 символов")
    private String category;

    public Menu() {
    }

    public Menu(String name, String description, Integer price, String category) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
