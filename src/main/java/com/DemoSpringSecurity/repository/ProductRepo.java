package com.DemoSpringSecurity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.DemoSpringSecurity.models.Product;

public interface ProductRepo extends JpaRepository<Product, Integer> {

    public List<Product> findAllProductsByCategoryId(int id);

    // public Optional<Category> findCategoryByProductId(int id);

}
