package com.DemoSpringSecurity.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DemoSpringSecurity.models.Product;
import com.DemoSpringSecurity.repository.ProductRepo;

@Service
public class ProductsService {
    @Autowired
    private ProductRepo productRepo;

    public List<Product> getProducts() {
        return productRepo.findAll();

    }

    public void saveProduct(Product product) {
        productRepo.save(product);
    }

    public void deleteCategory(int id) {
        productRepo.deleteById(id);
    }

    public List<Product> getProductByCategory(int id) {
        // Optional<Product> prodct = productRepo.findAllById(id);
        return productRepo.findAllProductsByCategoryId(id);
    }

    public void deleteProduct(int id) {
        productRepo.deleteById(id);
    }

    public Optional<Product> getProductById(int id) {
        Optional<Product> product = productRepo.findById(id);
        return product;
    }

    // public Optional<Category> getCategoryById(int Id) {
    // Optional<Category> categrory = productRepo.findCategoryByProductId(Id);

    // return categrory;
    // }

}
