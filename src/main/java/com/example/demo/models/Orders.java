package com.example.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.sql.Date;

@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Дата заказа не может быть пустой")
    private String date;
    @NotNull(message = "Номер стола не может быть пустым")
    @Positive(message = "Номер стола должен быть положительным")
    private int tableNumber;
    @NotNull(message = "Общая сумма заказа не может быть пустой")
    @Positive(message = "Сумма должена быть положительным")
    private double totalAmount;

    public Orders() {
    }

    public Orders(String date, int tableNumber, double totalAmount) {
        this.date = date;
        this.tableNumber = tableNumber;
        this.totalAmount = totalAmount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
}
