package org.kevin.services;




import org.kevin.dto.request.CategoryRequest;
import org.kevin.dto.response.CategoryResponse;

import java.util.List;

public interface CategoryService {
    List<CategoryResponse> getAllcategory();
    CategoryResponse getCategoryById(Long id);
    CategoryResponse createCategory(CategoryRequest categoryRequest);
    CategoryResponse updateCategory(Long id, CategoryRequest categoryRequest);
    boolean deleteCategory(Long id);
}
