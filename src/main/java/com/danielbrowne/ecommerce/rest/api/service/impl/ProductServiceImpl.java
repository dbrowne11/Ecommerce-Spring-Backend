package com.danielbrowne.ecommerce.rest.api.service.impl;

import com.danielbrowne.ecommerce.rest.api.entity.Category;
import com.danielbrowne.ecommerce.rest.api.entity.Product;
import com.danielbrowne.ecommerce.rest.api.entity.SubCategory;
import com.danielbrowne.ecommerce.rest.api.repository.ProductRepository;
import com.danielbrowne.ecommerce.rest.api.service.CategoryService;
import com.danielbrowne.ecommerce.rest.api.service.ProductService;
import com.danielbrowne.ecommerce.rest.api.service.SubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryService categoryService;

    @Autowired
    SubCategoryService subCategoryService;

    public Product createProductnoCat(Product product) {

        return productRepository.save(product);
    }

    @Override
    public Product createProduct(Long catId, Long subId, Product product) {
        Category cat = categoryService.getCategoryById(catId);
        SubCategory subCat = subCategoryService.getSubCategoryById(catId, subId);
        product.setSubCategory(subCat);
        product.setCategory(cat);
//        Set<Product> catProducts = cat.getProducts();
//        Set<Product> subCatProducts = subCat.getProducts();
//        catProducts.add(product);
//        subCatProducts.add(product);
//        cat.setProducts(catProducts);
//        subCat.setProducts(subCatProducts);
//        categoryService.updateCategory(catId, cat);
//        subCategoryService.updateSubCategory(catId, subCat);
        return productRepository.save(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getProductsByCatId(Long catId) {
        return productRepository.getProductByCategoryId(catId);
    }

    @Override
    public List<Product> getProductsBySubId(Long subId) {
        return productRepository.getProductsBySubCategoryId(subId);
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(
                        () -> new RuntimeException("Product Not Found")
                );
    }

    @Override
    public Product updateProduct(Product product) {
        Product old = getProductById(product.getId());
        old.setProductDescription(product.getProductDescription());
        old.setProductImage(product.getProductImage());
        old.setProductName(product.getProductName());
        old.setStatus(product.isStatus());
        old.setPosition(product.getPosition());
        productRepository.save(old);
        return old;
    }


    @Override
    public String deleteProduct(Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return "Deleted Successfully";
        }
        return "Entry does not exist";
    }
}
