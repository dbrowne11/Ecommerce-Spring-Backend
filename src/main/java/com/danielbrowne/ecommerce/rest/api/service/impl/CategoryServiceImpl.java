package com.danielbrowne.ecommerce.rest.api.service.impl;

import com.danielbrowne.ecommerce.rest.api.entity.Category;
import com.danielbrowne.ecommerce.rest.api.repository.CategoryRepository;
import com.danielbrowne.ecommerce.rest.api.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(
                        () -> new RuntimeException("Category Not found")
                );
    }

    @Override
    public Category updateCategory(Long id, Category category) {
        Category old = getCategoryById(id);
        old.setCategoryName(category.getCategoryName());
        old.setCategoryDescription(category.getCategoryDescription());
        old.setCategoryImage(category.getCategoryImage());
        old.setPosition(category.getPosition());
        old.setStatus(category.isStatus());
        categoryRepository.save(old);
        return old;
    }

    @Override
    public String deleteCategory(Long id) {
        if (categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
            return "Deleted Successfully";
        }
        return "Entry does not exist";
    }
}
