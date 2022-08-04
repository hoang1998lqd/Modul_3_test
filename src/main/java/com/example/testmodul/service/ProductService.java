package com.example.testmodul.service;

import com.example.testmodul.DAO.ProductRepository;
import com.example.testmodul.model.Product;

import java.sql.SQLException;
import java.util.ArrayList;

public class ProductService {
    private final ProductRepository productRepository = new ProductRepository();

    public ArrayList<Product> findAll() {
        return productRepository.findAll();
    }
    public void create(Product product) {
        productRepository.create(product);
    }
    public void update(Product product) throws SQLException {
        productRepository.update(product);
    }
    public void deleteById(int id) throws SQLException {
        productRepository.deleteById(id);
    }
    public Product findById(int id) throws SQLException {
        return productRepository.findById(id);
    }
    public ArrayList<Product> productSearch(String search){
        return productRepository.searchProduct(search);
    }

}
