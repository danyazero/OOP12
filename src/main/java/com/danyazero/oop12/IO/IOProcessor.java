package com.danyazero.oop12.IO;

import com.danyazero.oop12.models.Product;
import com.danyazero.oop12.service.ProductService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class IOProcessor implements IO {
    @Override
    public void writeObjectsToJson(List<Product> prods) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        try {
            objectMapper.writeValue(new File("products.json"), prods);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public List<Product> readObjectsFromJSON(){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.findAndRegisterModules();
        try {
            return objectMapper.readValue(new File("products.json"), new TypeReference<List<Product>>(){});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

