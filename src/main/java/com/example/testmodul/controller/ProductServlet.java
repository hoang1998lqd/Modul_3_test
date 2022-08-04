package com.example.testmodul.controller;

import com.example.testmodul.model.Category;
import com.example.testmodul.model.Product;
import com.example.testmodul.service.CategoryService;
import com.example.testmodul.service.ProductService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "ProductServlet", value = "/productServlet")
public class ProductServlet extends HttpServlet {
  ProductService productService = new ProductService();
    CategoryService categoryService = new CategoryService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null){
            action = "";
        }
        switch (action){
            case "edit":
                try {
                    updateProduct(request,response);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "delete":
                try {
                    deleteProduct(request,response);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            default:
                displayAllProduct(request,response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null){
            action = "";
        }
        switch (action){
            case "create":
                try {
                    addProduct(request,response);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "edit":
                try {
                    editProductByForm(request,response);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
        }
    }

    private void displayAllProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Product> products = productService.findAll();
        request.setAttribute("products", products);
        request.getRequestDispatcher("Home.jsp").forward(request,response);
    }

    private void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        productService.deleteById(id);
        ArrayList<Product> products = productService.findAll();
        request.setAttribute("products", products);
        displayAllProduct(request, response);
        request.getRequestDispatcher("index.jsp").forward(request,response);
    }
    private void updateProduct(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Product product = productService.findById(id);
        ArrayList<Category> categories = categoryService.findAll();
        request.setAttribute("categories",categories);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/create.jsp");
        request.setAttribute("product", product);
        requestDispatcher.forward(request, response);
    }

    private void addProduct(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        Product product = createProduct(request);
        ArrayList<Category> categories = categoryService.findAll();
        request.setAttribute("categories",categories);
        productService.create(product);
        response.sendRedirect("/product?action=");
    }
    private Product createProduct(HttpServletRequest request) throws SQLException {
        String name = request.getParameter("name");
        int price = Integer.parseInt(request.getParameter("price"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        String color = request.getParameter("color");
        String description = request.getParameter("description");
        int idCategory = Integer.parseInt(request.getParameter("idCategory"));
        ArrayList<Category> categories = categoryService.findAll();
        request.setAttribute("categories",categories);
        Category category = categoryService.findById(idCategory);
        return new Product(name,price,quantity,color,description,category);
    }
    private void editProductPost(HttpServletRequest request) throws SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        int price = Integer.parseInt(request.getParameter("price"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        String color = request.getParameter("color");
        String description = request.getParameter("description");
        int idCategory = Integer.parseInt(request.getParameter("idCategory"));
        ArrayList<Category> categories = categoryService.findAll();
        request.setAttribute("categories",categories);
        Category category = categoryService.findById(idCategory);
        Product product = productService.findById(id);
        product.setName(name);
        product.setPrice(price);
        product.setQuantity(quantity);
        product.setColor(color);
        product.setDescription(description);
        product.setCategory(category);
    }

    private void editProductByForm(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        editProductPost(request);
        ArrayList<Category> categories = categoryService.findAll();
        request.setAttribute("categories",categories);
        response.sendRedirect("/product?action=");
    }

}
