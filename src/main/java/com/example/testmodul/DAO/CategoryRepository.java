package com.example.testmodul.DAO;

import com.example.testmodul.connection.connectMySQL;
import com.example.testmodul.model.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CategoryRepository {
    private final connectMySQL connectMySQL = new connectMySQL();
    private final String SELECT_CATEGORY_BY_ID = "select * from category where id = ?;";
    private final String SELECT_ALL_CATEGORY = "select * from category;";
    private final String INSERT_CATEGORY = "insert into category(name)" + "value(?)";
    private final String UPDATE_CATEGORY = "update category set name = ? where id = ?";
    private final String DELETE_CATEGORY_BY_ID = "delete from category where id = ?";

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    public ArrayList<Category> findAll() {
        ArrayList<Category> categories = new ArrayList<>();
        try {
             connection = connectMySQL.getConnection();
             preparedStatement = connection.prepareStatement(SELECT_ALL_CATEGORY);
             resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                Category category = new Category(id, name);
                categories.add(category);
            }
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return categories;
    }

    public Category findById(int id) {
        try {
            Connection connection = connectMySQL.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CATEGORY_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                return new Category(id, name);
            }
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    public void update(Category category) {
        try {
            PreparedStatement preparedStatement = getPre(UPDATE_CATEGORY, category);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
    public void create(Category category) {
        try {
            PreparedStatement preparedStatement = getPre(INSERT_CATEGORY, category );
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    private PreparedStatement getPre(String SQL, Category category) throws SQLException {
        Connection connection = connectMySQL.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SQL);
        preparedStatement.setString(1, category.getName());
        return preparedStatement;
    }

    public void deleteById(int id) {
        try {
            Connection connection = connectMySQL.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CATEGORY_BY_ID);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }



}
