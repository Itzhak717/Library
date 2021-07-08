package com.librarySystem.demo.service;

import com.librarySystem.demo.model.Author;
import com.librarySystem.demo.model.Category;

public interface CategoryService {

    Category getCategory(String name);

    Category getCategory(long id);

    Iterable<Category> getCategory();

    Category createCategory(Category category);

    Category updateCategory(long id, Category category);

    void deleteCategory(long id);
}
