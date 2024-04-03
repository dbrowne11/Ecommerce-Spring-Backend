package com.danielbrowne.ecommerce.rest.api.service.impl;

import com.danielbrowne.ecommerce.rest.api.entity.Category;
import com.danielbrowne.ecommerce.rest.api.entity.SubCategory;
import com.danielbrowne.ecommerce.rest.api.repository.SubCategoryRepository;
import com.danielbrowne.ecommerce.rest.api.service.CategoryService;
import com.danielbrowne.ecommerce.rest.api.service.SubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class SubCategoryServiceImpl implements SubCategoryService {
    @Autowired
    SubCategoryRepository subCategoryRepository;
    @Autowired
    CategoryService categoryService;

    @Override
    public SubCategory createSubCategory(Long catId, SubCategory subCategory) {
        Category cat = categoryService.getCategoryById(catId);
        Set<SubCategory> catSubCats = cat.getSubCategories();
////
        subCategory.setCategory(cat);
//        catSubCats.add(subCategory);
//        cat.setSubCategories(catSubCats);
//        categoryService.updateCategory(catId, cat);
        return subCategoryRepository.save(subCategory);
    }

    @Override
    public List<SubCategory> getAllSubCategories() {
        return subCategoryRepository.findAll();
    }

    @Override
    public List<SubCategory> getSubCategoriesByCatId(Long catId) {
        return subCategoryRepository.findByCategoryId(catId);
    }

    @Override
    public SubCategory getSubCategoryById(Long catId, Long subId) {
        return subCategoryRepository.findById(subId)
                .orElseThrow(
                        () -> new RuntimeException("SubCategory not found")
                );
    }


    @Override
    public SubCategory updateSubCategory(Long catId, SubCategory subCategory) {
        SubCategory old = getSubCategoryById(catId, subCategory.getId());
        old.setSubCategoryName(subCategory.getSubCategoryName());
        old.setSubCategoryDescription(subCategory.getSubCategoryDescription());
        old.setSubCategoryImage(subCategory.getSubCategoryImage());
        old.setPosition(subCategory.getPosition());
        old.setStatus(subCategory.isStatus());
        subCategoryRepository.save(old);
        return old;
    }

//    @Override
//    public String deleteSubCategory(Long subId) {
//        if (subCategoryRepository.existsById(subId)) {
//            subCategoryRepository.deleteById(subId);
//            return "Deleted Successfully";
//        }
//        return "Entry does not exist";
//    }

    @Override
    public String deleteSubCategory(Long id) {
        if (subCategoryRepository.existsById(id)) {
            subCategoryRepository.deleteById(id);
            return "Deleted Successfully";
        }
        return "Entry does not exist";
    }
}
