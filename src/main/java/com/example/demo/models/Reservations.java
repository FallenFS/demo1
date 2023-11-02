package com.example.demo.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity
public class Reservations {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @NotNull(message = "Дата резервации не может быть пустой")
    private String date;
    @NotNull(message = "Номер столика не может быть пустым")
    @Positive(message = "Номер стола длжнет быть положитеельным")
    private int tableName;
    @ManyToOne
    @JoinColumn(name = "client_id")
    @NotNull(message = "Клиент не может быть пустым")
    private Client name;



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

    public int getTableName() {
        return tableName;
    }

    public void setTableName(int tableName) {
        this.tableName = tableName;
    }

    public Client getName() {
        return name;
    }

    public void setName(Client name) {
        this.name = name;
    }
}
