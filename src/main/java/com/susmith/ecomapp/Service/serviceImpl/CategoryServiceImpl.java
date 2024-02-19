package com.susmith.ecomapp.Service.serviceImpl;


import com.susmith.ecomapp.Entity.Category;
import com.susmith.ecomapp.Repository.CategoryRepository;
import com.susmith.ecomapp.Service.CategoryService;
import com.susmith.ecomapp.exception.CategoryNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Optional<Category> getCategoryById(Long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Long id, Category category) {

        Optional<Category> existingCategory = categoryRepository.findById(id);
        if(existingCategory.isPresent()){
            Category existingCat = existingCategory.get();
            existingCat.setName(category.getName());
            return categoryRepository.save(existingCat);
        }
        else{
            throw new CategoryNotFoundException("Category with ID " + id + " not found");
        }
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}
