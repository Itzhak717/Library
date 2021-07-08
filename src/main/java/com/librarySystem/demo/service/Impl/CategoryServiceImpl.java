package com.librarySystem.demo.service.Impl;

import com.librarySystem.demo.model.Category;
import com.librarySystem.demo.repository.CategoryRepository;
import com.librarySystem.demo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public Category getCategory(String name) {
        return categoryRepository.findByCategory(name).orElseThrow(RuntimeException::new);
    }

    @Override
    public Category getCategory(long id) {
        return categoryRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @Override
    public Iterable<Category> getCategory() {
        return categoryRepository.findAll();
    }

    @Override
    public Category createCategory(Category category) {
        if (getCategory(category.getCategory()) == null){
            return categoryRepository.save(category);
        }
        return category;
    }

    @Override
    public Category updateCategory(long id, Category category) {
        if (!categoryRepository.existsById(id)){
            throw new RuntimeException("not found");
        }
        category.setId(id);
        return categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(long id) {
        if (!categoryRepository.existsById(id)){
            throw new RuntimeException("not found");
        }
        categoryRepository.deleteById(id);
    }
}
