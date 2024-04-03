package com.danielbrowne.ecommerce.rest.api.repository;

import com.danielbrowne.ecommerce.rest.api.entity.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubCategoryRepository extends JpaRepository<SubCategory, Long> {
    public List<SubCategory> findByCategoryId(Long catId);

}
