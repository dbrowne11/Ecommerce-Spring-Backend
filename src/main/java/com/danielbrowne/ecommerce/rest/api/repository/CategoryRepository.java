package com.danielbrowne.ecommerce.rest.api.repository;

import com.danielbrowne.ecommerce.rest.api.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
