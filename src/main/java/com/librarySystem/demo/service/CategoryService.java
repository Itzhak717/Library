package com.librarySystem.demo.service;

import com.librarySystem.demo.model.Category;

public interface CategoryService {

    Category getCategoryByName(String name);

    Category getCategoryById(String id);

    Iterable<Category> getCategory();

    Category createCategory(Category category);

    Category updateCategory(String id, Category category);

    void deleteCategory(String id);
}
