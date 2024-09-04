package com.DemoSpringSecurity.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.DemoSpringSecurity.DTO.ProductDTO;
import com.DemoSpringSecurity.models.Category;
import com.DemoSpringSecurity.models.Product;
import com.DemoSpringSecurity.service.CategoryService;
import com.DemoSpringSecurity.service.ProductsService;

@Controller
public class AdminController {
    // String uploadDir = System.getProperty("user.dir") +
    // "/src/main/resources/static/productImages";
    String uploadDir = "C:\\Users\\hp\\Downloads\\DemoSpringSecurity\\DemoSpringSecurity\\src\\main\\resources\\static\\productImages";

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductsService productsService;

    @GetMapping("/adminHome")
    public String admin() {
        return "adminHome";
    }

    @GetMapping("/admin/categories")
    public ModelAndView getCategory() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("categories");
        List<Category> list = categoryService.getcategory();
        modelAndView.addObject("categories", list);
        return modelAndView;
    }

    @GetMapping("/admin/categories/add")
    public String addCategory(Model model) {

        model.addAttribute("category", new Category());

        return "categoriesAdd";
    }

    @PostMapping("/admin/categories/add")
    public String PostCategoryAdd(@ModelAttribute Category category) {
        categoryService.addCategory(category);
        System.out.println(category);
        return "redirect:/admin/categories";
    }

    @RequestMapping("/admin/categories/delete/{id}")
    public String deleteCategory(@PathVariable("id") int id) {
        System.out.println(id + "*******************");
        categoryService.deleteCategory(id);
        return "redirect:/admin/categories";

    }

    @GetMapping("/admin/categories/update/{id}")
    public ModelAndView updateCategory(@PathVariable("id") int id) {
        Category category = categoryService.updateCategory(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("categoriesAdd");
        modelAndView.addObject("category", category);
        System.out.println(category);
        return modelAndView;
    }

    // ************************Products Section */
    @GetMapping("/admin/products/add")
    public String getProductAdd(Model model) {
        model.addAttribute("productDTO", new ProductDTO());
        model.addAttribute("categories", categoryService.getcategory());
        return "productsAdd";

    }

    @GetMapping("/admin/products")
    public ModelAndView getProducts() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("products");
        List<Product> list = productsService.getProducts();
        modelAndView.addObject("products", list);
        return modelAndView;
    }

    @PostMapping("/admin/products/add")
    public String PostproductsAdd(@ModelAttribute ProductDTO productDTO,
            @RequestParam("productImage") MultipartFile file, @RequestParam("imgName") String imgName)
            throws IOException {
        Product product = new Product();
        product.setId(productDTO.getId());
        product.setName(productDTO.getName());
        // System.out.println(productDTO.getImageName());it is null
        product.setCategory(categoryService.getCategoryById(productDTO.getCategoryId()).get());
        product.setDescription(productDTO.getDescription());
        // product.setImageName(file.getOriginalFilename());
        product.setPrice(productDTO.getPrice());
        product.setWeight(productDTO.getWeight());
        System.out.println(productDTO);
        String imageUUID = file.getOriginalFilename();
        if (!file.isEmpty()) {
            imageUUID = file.getOriginalFilename();
            Path fileNameAndPath = Paths.get(uploadDir, imageUUID);
            Files.write(fileNameAndPath, file.getBytes());

        } else {
            imageUUID = imgName;

        }

        product.setImageName(imageUUID);
        productsService.saveProduct(product);

        return "redirect:/admin/products";
    }

    @RequestMapping("/admin/product/delete/{id}")
    public String deleteById(@PathVariable("id") int id) {
        productsService.deleteProduct(id);
        return "redirect:/admin/products";
    }

    @RequestMapping("/admin/product/update/{id}")
    public String UpdateProductsById(@PathVariable("id") int id, Model model) {
        // ModelAndView modelAndView = new ModelAndView();
        ProductDTO productDTO = new ProductDTO();
        Product product = productsService.getProductById(id).get();
        productDTO.setName(product.getName());
        productDTO.setId(product.getId());
        productDTO.setImageName(product.getImageName());
        System.out.println(productDTO.getImageName());
        productDTO.setDescription(product.getDescription());
        productDTO.setPrice(product.getPrice());
        productDTO.setWeight(product.getWeight());
        // productDTO.setCategoryId((productsService.getCategoryById(id)).get().getId());
        // productDTO.setCategoryId(product.getCategory().getId());
        // Category categories = product.getCategory();
        List<Category> categories = categoryService.findAllCategory();
        model.addAttribute("productDTO", productDTO);
        model.addAttribute("categories", categories);
        return "productsAdd";
    }

}
