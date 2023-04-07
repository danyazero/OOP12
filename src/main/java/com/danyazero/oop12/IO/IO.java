package com.danyazero.oop12.IO;

import com.danyazero.oop12.models.Product;
import com.danyazero.oop12.service.ProductService;

import java.util.List;

public interface IO {
    public boolean writeObjectsToJson(List<Product> products);
    public List<Product> readObjectsFromJSON();
}
