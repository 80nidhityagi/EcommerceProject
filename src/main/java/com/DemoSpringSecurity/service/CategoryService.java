package com.DemoSpringSecurity.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DemoSpringSecurity.models.Category;
import com.DemoSpringSecurity.repository.CategoryRepo;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepo categoryRepo;

    public void addCategory(Category category) {
        categoryRepo.save(category);

    }

    public List<Category> getcategory() {
        return categoryRepo.findAll();
    }

    public void deleteCategory(int id) {
        categoryRepo.deleteById(id);
    }

    public Optional<Category> getCategoryById(int id) {
        Optional<Category> cOptional = categoryRepo.findById(id);
        return cOptional;
    }

    public Category updateCategory(int id) {
        Category category = categoryRepo.findById(id).get();

        return category;
    }

    public List<Category> findAllCategory() {
        return categoryRepo.findAll();
    }

}
