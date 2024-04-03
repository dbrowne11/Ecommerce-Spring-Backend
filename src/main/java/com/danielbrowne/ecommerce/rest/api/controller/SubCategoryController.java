package com.danielbrowne.ecommerce.rest.api.controller;

import com.danielbrowne.ecommerce.rest.api.entity.Category;
import com.danielbrowne.ecommerce.rest.api.entity.SubCategory;
import com.danielbrowne.ecommerce.rest.api.service.CategoryService;
import com.danielbrowne.ecommerce.rest.api.service.SubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/subcategories")

public class SubCategoryController {
    @Autowired
    SubCategoryService subCategoryService;



    @PostMapping("/{catId}")
    ResponseEntity<SubCategory> createSubCategory(
            @PathVariable("catId") Long catId,
            @RequestBody SubCategory subCategory) {

        SubCategory data = subCategoryService.createSubCategory(catId, subCategory);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @GetMapping
    ResponseEntity<List<SubCategory>> getAllSubCategories() {
        List<SubCategory> data =  subCategoryService.getAllSubCategories();
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @GetMapping("/{catId}/{subId}")
    ResponseEntity<SubCategory> getSubCategoryById(@PathVariable("catId") Long catId, @PathVariable("subId") Long subId) {
        SubCategory data = subCategoryService.getSubCategoryById(catId, subId);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    ResponseEntity<SubCategory> putSubCategory(@PathVariable("id") Long id, @RequestBody SubCategory subCategory) {
        SubCategory data = subCategoryService.updateSubCategory(id, subCategory);
        return new ResponseEntity<>(data, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteSubCategory(@PathVariable("id") Long id) {

        try {
            String data = subCategoryService.deleteSubCategory(id);
            return new ResponseEntity<>(data, HttpStatus.OK);
        } catch (RuntimeException ex) {
            return new ResponseEntity<>("Entry not found", HttpStatus.NOT_FOUND);
        }
    }
}
