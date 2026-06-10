package org.example.restexam.controller;

import org.example.restexam.dto.ProductDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api2/products")
public class ProductController2 {

    @GetMapping(value = "/xml", produces = "application/xml")
    public ResponseEntity<List<ProductDTO>> getProductsAsXml(){
        List<ProductDTO> products = Arrays.asList(
                new ProductDTO(1L, "Laptop", 999),
                new ProductDTO(2L, "Mouse", 29)
        );

        return ResponseEntity.ok(products);
    }
    @GetMapping
    public List<ProductDTO> getProducts(){
        List<ProductDTO> products = Arrays.asList(
                new ProductDTO(1L, "Laptop", 999),
                new ProductDTO(2L, "Mouse", 29)
        );

        return products;
    }

    @PostMapping
    public ProductDTO createProduct(@RequestBody ProductDTO productDTO){

        return productDTO;
    }

    @PutMapping("/{id}")
    public ProductDTO updateProduct(@RequestBody ProductDTO productDTO){

        return productDTO;
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(){
        return "삭제성공!!";
    }

}
