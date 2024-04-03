package com.danielbrowne.ecommerce.rest.api.service;

import com.danielbrowne.ecommerce.rest.api.entity.Category;

import java.util.List;

public interface CategoryService {
    // Create
    public Category createCategory(Category category);
    // Read
    public List<Category> getAllCategories();
    public Category getCategoryById(Long id);
    // Update
    public Category updateCategory(Long id, Category category);

    public String deleteCategory(Long id);

}
