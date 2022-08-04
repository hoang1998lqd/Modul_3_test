package com.example.testmodul.DAO;

import com.example.testmodul.connection.connectMySQL;
import com.example.testmodul.model.Category;
import com.example.testmodul.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductRepository {
    private final com.example.testmodul.connection.connectMySQL connectMySQL = new connectMySQL();
    private final CategoryRepository categoryRepository = new CategoryRepository();
    private final String SELECT_ALL_PRODUCT = "select * from product";
    private final String INSERT_PRODUCT = "insert into product(name, price, quantity, color, description,category_id)" +
            "value(?,?,?,?,?,?)";
    private final String DELETE_PRODUCT_BY_ID = "delete from product where id = ?";
    private final String SELECT_PRODUCT_BY_ID = "select * from product where id = ?";
    private final String UPDATE_PRODUCT = "update product set name = ?, price = ?, quantity = ?, color = ?" +
            ", description = ?,category_id = ? where id = ?";
    private final String SELECT_PRODUCT_BY_CATEGORY = "select * from product where product.category_id = ?";
    private final String SELECT_SEARCH = "select * from product where name like ?";


    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    private Product getProduct(int id, ResultSet resultSet) throws SQLException {
        String name = resultSet.getString("name");
        int price = resultSet.getInt("price");
        int quantity = resultSet.getInt("quantity");
        String color = resultSet.getString("color");
        String description = resultSet.getString("description");
        int category_id = resultSet.getInt("category_id");
        Category category = categoryRepository.findById(category_id);
        return  new Product(id,name,price,quantity,color,description,category);
    }

    public Product findById(int id) {
        try {
            connection = connectMySQL.getConnection();
             preparedStatement = connection.prepareStatement(SELECT_PRODUCT_BY_ID);
            preparedStatement.setInt(1, id);
             resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                return getProduct(id, resultSet);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    public ArrayList<Product> findAll() {
        ArrayList<Product> products = new ArrayList<>();
        try {
             connection = connectMySQL.getConnection();
             preparedStatement = connection.prepareStatement(SELECT_ALL_PRODUCT);
             resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                Product product = getProduct(id, resultSet);
                products.add(product);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return products;
    }

    public void create(Product product) {
        try {
            PreparedStatement preparedStatement = getPreparedStatement(INSERT_PRODUCT, product);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    private PreparedStatement getPreparedStatement(String SQL, Product product) throws SQLException {
         connection = connectMySQL.getConnection();
         preparedStatement = connection.prepareStatement(SQL);
        preparedStatement.setString(1, product.getName());
        preparedStatement.setInt(2, product.getPrice());
        preparedStatement.setInt(3, product.getQuantity());
        preparedStatement.setString(4, product.getColor());
        preparedStatement.setString(5, product.getDescription());
        preparedStatement.setInt(6,product.getCategory().getId());
        return preparedStatement;
    }

    public void deleteById(int id) {
        try {
             connection = connectMySQL.getConnection();
             preparedStatement = connection.prepareStatement(DELETE_PRODUCT_BY_ID);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public void update(Product product) {
        try {
            PreparedStatement preparedStatement = getPreparedStatement(UPDATE_PRODUCT, product);
            preparedStatement.setInt(7,product.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());;
        }
    }

    // Tìm kiếm gần đúng
    public ArrayList<Product>searchProduct(String search){
        ArrayList<Product> products = new ArrayList<>();
        try{
            connection = connectMySQL.getConnection();
            preparedStatement = connection.prepareStatement(SELECT_SEARCH);
            preparedStatement.setString(1,"%" + search + "%");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int idProduct = resultSet.getInt("id");
                products.add(findById(idProduct));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return products;
    }



}
