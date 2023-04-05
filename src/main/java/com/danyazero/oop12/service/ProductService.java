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
import java.util.Scanner;

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
        return getSortedProducts(productsByName);
    }

    public void save() {
        IO io = new IOProcessor();
        io.writeObjectsToJson(products);
    }

    public List<Product> getSortedArrayByPrice(List<Product> products) {

        products.sort((o1, o2) -> {
            int k = Double.compare(o1.getPrice()*o1.getPrice(), o2.getPrice()*o2.getCount());
            if(k != 0) return k;
            return -Double.compare(o1.getPrice(), o2.getPrice());
        });

        return products;
    }

    public List<Product> getSortedProducts(List<Product> products){

        products.sort((o1, o2) -> {
            if (o1.getTerm().isAfter(o2.getTerm())) return -1;
            if (o1.getTerm().isBefore(o2.getTerm())) return 1;
            return 0;
        });

        return products;
    }
}
