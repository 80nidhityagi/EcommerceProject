package com.DemoSpringSecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.DemoSpringSecurity.service.CategoryService;
import com.DemoSpringSecurity.service.ProductsService;

@Controller
public class HomeController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductsService productsService;

    @GetMapping("/Home")
    public String home() {
        return "index";
    }

    @GetMapping("/shop")
    public String shop(Model model) {
        model.addAttribute("categories", categoryService.findAllCategory());
        model.addAttribute("products", productsService.getProducts());
        return "shop";

    }

    @GetMapping("/shop/category/{id}")
    public String productByCategoryId(Model model, @PathVariable int id) {
        model.addAttribute("categories", categoryService.findAllCategory());
        model.addAttribute("products", productsService.getProductByCategory(id));
        return "shop";

    }

    @GetMapping("/shop/viewproduct/{id}")
    public String viewproduct(Model model, @PathVariable int id) {
        model.addAttribute("product", productsService.getProductById(id).get());
        return "viewProduct";

    }

}
