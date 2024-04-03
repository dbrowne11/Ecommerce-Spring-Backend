package com.danielbrowne.ecommerce.rest.api.service;

import com.danielbrowne.ecommerce.rest.api.entity.Product;

import java.util.List;

public interface ProductService {
    Product createProduct(Long catId, Long subId, Product product);

    public Product createProductnoCat(Product product);
    List<Product> getAllProducts();
    List<Product> getProductsByCatId(Long catId);
    List<Product> getProductsBySubId(Long subId);
    Product getProductById(Long id);

    Product updateProduct(Product product);

    String deleteProduct(Long id);
}
