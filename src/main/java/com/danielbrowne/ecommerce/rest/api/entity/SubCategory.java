package com.danielbrowne.ecommerce.rest.api.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="subcategories")
public class SubCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "sub_category_name", nullable = false, unique = true)
    private String subCategoryName;
    @Column(name = "sub_category_description")
    private String subCategoryDescription;
    @Column(name = "sub_category_image")
    private String subCategoryImage;
    private int position;
    private boolean status;
    @ManyToOne(
            fetch = FetchType.LAZY,
            cascade = CascadeType.PERSIST
    )
    @JoinColumn(
            name="category_id", nullable = false
    )
//    @EqualsAndHashCode.Exclude
    @JsonBackReference
    private Category category;

    @OneToMany(
        mappedBy = "subCategory",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
//    @EqualsAndHashCode.Exclude
//    @JoinTable(
//            name="sub_category_products"
//    )
//    @JsonManagedReference
    private Set<Product> products = new HashSet<Product>();
}
