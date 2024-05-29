package org.kevin.services.impl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.kevin.dto.request.CategoryRequest;
import org.kevin.dto.response.CategoryResponse;
import org.kevin.entities.Category;
import org.kevin.repositories.CategoryRepository;
import org.kevin.services.CategoryService;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class CategoryServiceImpl implements CategoryService {
    @Inject
    CategoryRepository categoryRepository;

    @Override
    public List<CategoryResponse> getAllcategory() {
        List<Category> categories = categoryRepository.listAll();
        return categories.stream()
                .map(category -> CategoryResponse.builder()
                        .id(category.getId())
                        .name(category.getName())
                        .description(category.getDescription())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public CategoryResponse getCategoryById(Long id) {
        Category existingCategory = categoryRepository.findById(id);
        if (existingCategory == null) {
            return null;
        }
        return CategoryResponse.builder()
                .id(existingCategory.getId())
                .name(existingCategory.getName())
                .description(existingCategory.getDescription())
                .build();
    }

    @Override
    @Transactional
    public CategoryResponse createCategory(CategoryRequest categoryRequest) {
        Category category = Category.builder()
                .name(categoryRequest.getName())
                .description(categoryRequest.getDescription())
                .build();

        categoryRepository.persist(category);

        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .build();
    }

    @Override
    @Transactional
    public CategoryResponse updateCategory(Long id, CategoryRequest categoryRequest) {
        Category existingCategory = categoryRepository.findById(id);
        if (existingCategory == null) {
            return null;
        }
        existingCategory.setName(categoryRequest.getName());
        existingCategory.setDescription(categoryRequest.getDescription());

        categoryRepository.persist(existingCategory);

        return CategoryResponse.builder()
                .id(existingCategory.getId())
                .name(existingCategory.getName())
                .description(existingCategory.getDescription())
                .build();
    }

    @Override
    @Transactional
    public boolean deleteCategory(Long id) {
        Category existingCategory = categoryRepository.findById(id);

        if (existingCategory != null) {
            categoryRepository.delete(existingCategory);
            return true;
        } else {
            return false;
        }
    }



}
