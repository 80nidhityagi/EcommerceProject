package com.DemoSpringSecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.DemoSpringSecurity.models.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
