package com.danyazero.oop12.service;

import com.danyazero.oop12.IO.IO;
import com.danyazero.oop12.IO.IOProcessor;
import com.danyazero.oop12.models.Product;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@NoArgsConstructor
public class ProductService {
    @Getter
    private List<Product> products = new ArrayList<>();

    @PostConstruct
    public void init() {
        IO io = new IOProcessor();
        products = io.readObjectsFromJSON();
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void deleteById(int id){
        products.removeIf(p -> (p.getId() == id));
    }

    public void save() {
        IO io = new IOProcessor();
        io.writeObjectsToJson(products);
    }
}
