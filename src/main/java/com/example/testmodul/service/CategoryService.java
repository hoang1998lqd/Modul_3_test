package com.example.testmodul.service;

import com.example.testmodul.DAO.CategoryRepository;
import com.example.testmodul.model.Category;

import java.sql.SQLException;
import java.util.ArrayList;

public class CategoryService {
    CategoryRepository categoryService = new CategoryRepository();

    public ArrayList<Category> findAll() {
        return categoryService.findAll();
    }
    public void create(Category category) {
        categoryService.create(category);
    }
    public void update(Category category) throws SQLException {
        categoryService.update(category);
    }
    public void deleteById(int id) throws SQLException {
        categoryService.deleteById(id);
    }
    public Category findById(int id) throws SQLException {
        return categoryService.findById(id);
    }
}
