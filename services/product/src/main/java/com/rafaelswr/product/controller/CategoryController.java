package com.rafaelswr.product.controller;

import com.rafaelswr.product.product.CategoryDTO;
import com.rafaelswr.product.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createNewCategory(@RequestBody CategoryDTO categoryDTO){
        return new ResponseEntity<>(categoryService.createNewCategory(categoryDTO), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<CategoryDTO>> getAllCategories(){
        return new ResponseEntity<>(categoryService.getAllCategories(), HttpStatus.OK);
    }


}
