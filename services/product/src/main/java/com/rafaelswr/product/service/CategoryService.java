package com.rafaelswr.product.service;

import com.rafaelswr.product.product.Category;
import com.rafaelswr.product.product.CategoryDTO;
import com.rafaelswr.product.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    private CategoryDTO toCategoryDTO(Category category){
        return CategoryDTO.builder()
                .name(category.getName())
                .description(category.getDescription())
                .build();
    }

    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.findAll().stream().map(this::toCategoryDTO).toList();
    }
}

