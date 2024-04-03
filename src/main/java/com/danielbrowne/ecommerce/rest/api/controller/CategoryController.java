package com.danielbrowne.ecommerce.rest.api.controller;

import com.danielbrowne.ecommerce.rest.api.entity.Category;
import com.danielbrowne.ecommerce.rest.api.entity.Category;
import com.danielbrowne.ecommerce.rest.api.entity.Product;
import com.danielbrowne.ecommerce.rest.api.service.CategoryService;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @PostMapping
    ResponseEntity<Category> createCategory(@RequestBody Category category) {
        Category data = categoryService.createCategory(category);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }
    
    @GetMapping
    ResponseEntity<List<Category>> getAllCategories() {
        List<Category> data =  categoryService.getAllCategories();
//        HttpHeaders headers = new HttpHeaders();
//        headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, "Access-Control-Allow-Methods");
//        headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*");
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<Category> getCategoryById(@PathVariable("id") Long id) {
        Category data = categoryService.getCategoryById(id);
        if (data == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    ResponseEntity<Category> putCategory(@PathVariable("id") Long id, @RequestBody Category category) {
        Category data = categoryService.updateCategory(id, category);
        if (data == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(data, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteCategory(@PathVariable("id") Long id) {
        String data = categoryService.deleteCategory(id);
        if (data.equals("Deleted Successfully")) {
            return new ResponseEntity<>(data, HttpStatus.OK);
        }
        return new ResponseEntity<>("Entry not found", HttpStatus.NOT_FOUND);

    }
}
