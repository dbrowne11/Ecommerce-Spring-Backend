package com.danielbrowne.ecommerce.rest.api.repository;

import com.danielbrowne.ecommerce.rest.api.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    public List<Product> getProductByCategoryId(Long catId);
    public List<Product> getProductsBySubCategoryId(Long subCatId);
}
