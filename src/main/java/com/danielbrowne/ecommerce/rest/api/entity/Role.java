package com.danielbrowne.ecommerce.rest.api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String name;

////    @ManyToMany
////    @JoinTable(
////            name="users_roles",
////            joinColumns = @JoinColumn(name="role_id"),
////            inverseJoinColumns = @JoinColumn(name="user_id")
////    )
//    Set<User> users;
}
