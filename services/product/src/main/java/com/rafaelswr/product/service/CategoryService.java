package com.rafaelswr.product.service;

import com.rafaelswr.product.product.Category;
import com.rafaelswr.product.product.CategoryDTO;
import com.rafaelswr.product.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    public String createNewCategory(CategoryDTO categoryDTO) {
        categoryRepository.save(toCategory(categoryDTO));
        return "Category is now registered! ";
    }

    private Category toCategory(CategoryDTO categoryDTO){
        return Category.builder()
                .name(categoryDTO.name())
                .description(categoryDTO.description())
                .build();
    }
}

