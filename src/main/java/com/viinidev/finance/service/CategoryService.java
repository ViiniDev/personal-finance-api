package com.viinidev.finance.service;

import com.viinidev.finance.domain.Category;
import com.viinidev.finance.dto.CategoryRequest;
import com.viinidev.finance.dto.CategoryResponse;
import com.viinidev.finance.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public CategoryResponse create(CategoryRequest request) {
        if (categoryRepository.existsByNameIgnoreCase(request.name())) {
            throw new IllegalArgumentException("Category already exists.");
        }
        Category category = new Category(request.name(), request.type());
        return CategoryResponse.from(categoryRepository.save(category));
    }

    public List<CategoryResponse> list() {
        return categoryRepository.findAll().stream().map(CategoryResponse::from).toList();
    }

    public Category getById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Category not found."));
    }
}
