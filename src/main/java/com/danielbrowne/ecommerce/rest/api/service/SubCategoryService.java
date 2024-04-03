package com.danielbrowne.ecommerce.rest.api.service;


import com.danielbrowne.ecommerce.rest.api.entity.Category;
import com.danielbrowne.ecommerce.rest.api.entity.SubCategory;

import java.util.List;

public interface SubCategoryService {
    public SubCategory createSubCategory(Long catId, SubCategory category);
    public List<SubCategory> getAllSubCategories();
    public List<SubCategory> getSubCategoriesByCatId(Long catId);
    public SubCategory getSubCategoryById(Long catId, Long subId);

    public SubCategory updateSubCategory(Long catId, SubCategory subCategory);
    public String deleteSubCategory(Long subId);
}
