package com.danyazero.oop12.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
public class Product {
    private int id;
    private String name;
    private String manufacturer;
    private double price;
    private LocalDate term;
    private int count;

    private static int uid = 0;

    public Product(){};

    public Product(String name, String manufacturer, double cost, LocalDate term, int count) {
        uid++;
        this.id = uid;
        this.name = name;
        this.manufacturer = manufacturer;
        this.price = cost;
        this.term = term;
        this.count = count;
    }
}
