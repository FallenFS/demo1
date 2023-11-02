package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.domain.Sort;

@Entity
public class OrderedDishes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id") // Связь с заказом
    @NotNull(message = "Заказ не может быть пустым")
    private Orders order;

    @ManyToOne
    @JoinColumn(name = "dish_id") // Связь с блюдом
    @NotNull(message = "Блюдо не может быть пустым")
    private Menu dish;

    @Min(value = 1, message = "Количество заказанных порций должно быть больше 0")
    private int quantity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Orders getOrder() {
        return order;
    }

    public void setOrder(Orders order) {
        this.order = order;
    }

    public Menu getDish() {
        return dish;
    }

    public void setDish(Menu dish) {
        this.dish = dish;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
