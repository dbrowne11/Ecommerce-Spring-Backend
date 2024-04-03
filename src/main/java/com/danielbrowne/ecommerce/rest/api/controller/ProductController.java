package com.danielbrowne.ecommerce.rest.api.controller;

import com.danielbrowne.ecommerce.rest.api.entity.Category;
import com.danielbrowne.ecommerce.rest.api.entity.Product;
import com.danielbrowne.ecommerce.rest.api.entity.SubCategory;
import com.danielbrowne.ecommerce.rest.api.service.CategoryService;
import com.danielbrowne.ecommerce.rest.api.service.ProductService;
import com.danielbrowne.ecommerce.rest.api.service.SubCategoryService;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    SubCategoryService subCategoryService;

    @PostMapping(value = "/{catId}/{subId}")
    ResponseEntity<Product> createProduct(
            @PathVariable("catId") Long catId,
            @PathVariable("subId") Long subId,
            @RequestBody Product product) {
        Product data = productService.createProduct(catId, subId, product);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }
    
    @GetMapping
    ResponseEntity<List<Product>> getAllProducts() {
        List<Product> data =  productService.getAllProducts();
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<Product> getProductById(@PathVariable("id") Long id) {
        Product data = productService.getProductById(id);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @GetMapping("/cat/{catId}")
    ResponseEntity<List<Product>> getProductByCatId(@PathVariable("catId") Long catId) {
        List<Product> data = productService.getProductsByCatId(catId);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @GetMapping("/subcat/{subCatId}")
    ResponseEntity<List<Product>> getProductBySubCatId(@PathVariable("subCatId") Long subCatId) {
        List<Product> data = productService.getProductsBySubId(subCatId);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @PutMapping
    ResponseEntity<Product> putProduct(@RequestBody Product product) {
        Product data = productService.updateProduct(product);
        return new ResponseEntity<>(data, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteProduct(@PathVariable("id") Long id) {

        try {
            String data = productService.deleteProduct(id);
            return new ResponseEntity<>(data, HttpStatus.OK);
        } catch (RuntimeException ex) {
            return new ResponseEntity<>("Entry not found", HttpStatus.NOT_FOUND);
        }
    }
}
