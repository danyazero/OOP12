package com.danyazero.oop12.controllers;

import com.danyazero.oop12.models.Product;
import com.danyazero.oop12.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
@AllArgsConstructor
public class ProductController {

    private ProductService productService;

    @GetMapping("/products")
    public String showStudents(Model model) {
        model.addAttribute("products", productService.getProducts());
        return "products";
    }

    @GetMapping("/delete_product")
    public String deleteStudent(@RequestParam int id) {
        productService.deleteById(id);
        return "redirect:/products";
    }

    @PostMapping("/add_product")
    public String addStudent(
            @RequestParam("product_name") String name,
            @RequestParam("product_manufacturer") String manufacturer,
            @RequestParam("product_price") double price,
            @RequestParam("product_count") int count,
            @RequestParam("product_term") LocalDate date

    ) {
        Product product = new Product(name, manufacturer, price, date, count);
        productService.addProduct(product);
        return "redirect:/products";
    }

    @GetMapping("/save_products")
    public String saveStudents() {
        productService.save();
        return "redirect:/products";
    }
}