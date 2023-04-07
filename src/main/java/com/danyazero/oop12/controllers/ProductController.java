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
    public String showProducts(
            @RequestParam(name = "type", defaultValue = "0") int type,
            @RequestParam(name = "v1", defaultValue = "false") Boolean v1,
            @RequestParam(name = "v2", defaultValue = "false") Boolean v2,
            @RequestParam(name = "name", defaultValue = "") String name,
            Model model
    ) {

        switch (type) {
            case 0 -> {
                model.addAttribute("products", productService.getProducts());
            }
            case 1 -> {
                model.addAttribute("products", productService.getProductsByName(productService.getProducts(), name));
            }
        }
        model.addAttribute("v1", v1);
        model.addAttribute("v2", v2);
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

    @PostMapping("/products")
    public String sortByName(
            @RequestParam(name = "sort_product_name", required = false) String name,
            @RequestParam(name = "sort_product_price", defaultValue = "0.0") double pPrice,
            @RequestParam(name = "sort_product_date", required = false) LocalDate date,
            @RequestParam(name = "type", required = true) int type,
            Model model
    ) {
        model.addAttribute("v1", false);
        model.addAttribute("v2", false);
        switch (type) {
            case 1 -> {
                model.addAttribute("products", productService.getProductByNameAndPrice(productService.getProducts(), name, pPrice));
                return "products";
            }
            case 2 -> {
                model.addAttribute("products", productService.getProductByTerm(productService.getProducts(), date));
                return "products";
            }
        }
        return "redirect:/products";
    }
}
