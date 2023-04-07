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

    public List<Product> getProductsByName(List<Product> products, String name) {
        List<Product> productsByName = new ArrayList<>();
        for (Product product : products) {
            if (product.getName().equals(name)) {
                productsByName.add(product);
            }
        }
        return productsByName;
    }

    public List<Product> getProductByTerm(List<Product> products, LocalDate term) {
        List<Product> productsByTerm = new ArrayList<>();
        for (Product product : products) {
            if (product.getTerm().isAfter(term)) {
                productsByTerm.add(product);
            }
        }
        return productsByTerm;
    }

    public List<Product> getProductByNameAndPrice(List<Product> products, String name, double cost) {
        List<Product> productsByPrice = new ArrayList<>();
        for (Product product : products) {
            if (product.getName().equals(name) && (product.getPrice() <= cost)) {
                productsByPrice.add(product);
            }
        }
        return productsByPrice;
    }

    public void save() {
        IO io = new IOProcessor();
        io.writeObjectsToJson(products);
    }


}
