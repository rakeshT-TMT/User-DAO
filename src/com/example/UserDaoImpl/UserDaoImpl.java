package com.example.UserDaoImpl;

import com.example.Model.User;
import com.example.UserDao.UserDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {



        private Connection connection;
        private PreparedStatement preparedStatement;

        final static String INSERT_QUEARY = "INSERT INTO `user`" +
                "(userId, username, password, email,address, role) " +
                "values(?,?,?,?,?,?)";
        final static String SELECT_QUEARY = "SELECT * from user where userId=?";
        final static String UPDATE_QUEARY = "UPDATE user SET username=?,password=?,email=?,address=?,role=? Where userId=?";
        final static String DELETE_QUEARY = "DELETE FROM user where userId=?";
        final static String SELECT_ALL_QUEARY = "SELECT * FROM user";

    public UserDaoImpl() {
            String url = "jdbc:mysql://localhost:3306/my_db1";
            String username = "root";
            String password = "root";
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(url, username, password);
            } catch (ClassNotFoundException | SQLException e) {
                throw new RuntimeException(e);
            }

        }

        @Override
        public void addUser (User user){

            try {

                preparedStatement = connection.prepareStatement(INSERT_QUEARY);
                preparedStatement.setInt(1, user.getUserId());
                preparedStatement.setString(2, user.getUsername());
                preparedStatement.setString(3, user.getPassword());
                preparedStatement.setString(4, user.getEmail());
                preparedStatement.setString(5, user.getAddress());
                preparedStatement.setString(6, user.getRole());

                preparedStatement.executeUpdate();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public User getUser ( int userId){
            PreparedStatement statement = null;
            ResultSet res = null;
            User user = null;
            try {
                statement = connection.prepareStatement(SELECT_QUEARY);
                statement.setInt(1, userId);
                res = statement.executeQuery();
                if (res.next()) {
                    int id = res.getInt("userId");
                    String username = res.getString("username");
                    String password = res.getString("password");
                    String email = res.getString("email");
                    String address = res.getString("address");
                    String role = res.getString("role");
                    Date createDate = res.getDate("createDate");
                    Date lastLoginDate = res.getDate("lastLoginDate");

                    user = new User(userId, username, email, password, address, role, createDate, lastLoginDate);
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return null;
        }

        @Override
        public void updateUser (User user){
            PreparedStatement statement = null;
            try {
                statement = connection.prepareStatement(UPDATE_QUEARY);
                statement.setString(1, user.getUsername());
                statement.setString(2, user.getPassword());
                statement.setString(3, user.getEmail());
                statement.setString(4, user.getAddress());
                statement.setString(5, user.getRole());
                statement.setInt(1, user.getUserId());

                statement.executeUpdate();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }

        @Override
        public void deleteUser (int userId){
            PreparedStatement statement = null;

            try {
                statement = connection.prepareStatement(DELETE_QUEARY);
                statement.setInt(1, userId);
                statement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }

        @Override
        public List<User> getAllUser() {
            Statement statement = null;
            ResultSet res = null;
            ArrayList<User> usersList = new ArrayList<User>();
            try {
                statement = connection.createStatement();
                res = statement.executeQuery(SELECT_ALL_QUEARY);

                while (res.next()) {
                    int userId = res.getInt("userId");
                    String username = res.getString("username");
                    String password = res.getString("password");
                    String email = res.getString("email");
                    String address = res.getString("address");
                    String role = res.getString("role");
                    Date createDate = res.getDate("createDate");
                    Date lastLoginDate = res.getDate("lastLoginDate");
                    User user = new User(userId, username, email, password, address, role, createDate, lastLoginDate);

                    usersList.add(user);
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            return usersList;
        }

}
