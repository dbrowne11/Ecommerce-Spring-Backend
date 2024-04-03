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

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_name", nullable = false, unique = true)
    private String productName;

    @Column(name = "product_description")
    private String productDescription;

    @Column(name = "product_image")
    private String productImage;

    private double price;

    private int position;

    private boolean status;

    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name="category_id", nullable = false
    )
    @EqualsAndHashCode.Exclude
    @JsonBackReference
    private Category category;


    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name="sub_category_id", nullable = true
    )
//    @EqualsAndHashCode.Exclude
//    @JsonBackReference
    @JsonIgnore
    private SubCategory subCategory;


}