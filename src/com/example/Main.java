package com.example;

import com.example.Model.User;
import com.example.UserDao.UserDao;
import com.example.UserDaoImpl.UserDaoImpl;

import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Initialize UserDao implementation
        UserDao userDao = new UserDaoImpl();

        // Add a new user
        User newUser = new User(2, "Babu", "babu.@example.com", "password123", "123 Street", "user", new Date(), new Date());
        userDao.addUser(newUser);
        System.out.println("User added successfully.");

//        // Try to retrieve the user
//        User retrievedUser = userDao.getUser(1);
//        if (retrievedUser != null) {
//            System.out.println("User found: " + retrievedUser.getUsername());
//            retrievedUser.setUsername("newUsername");
//            userDao.updateUser(retrievedUser);
//            System.out.println("User updated successfully.");
//        } else {
//            System.out.println("User not found.");
//        }

//        // Update the User
//        retrievedUser.setUsername("john_doe_updated");
//        retrievedUser.setEmail("john_updated@example.com");
//        userDao.updateUser(retrievedUser);
//        System.out.println("User updated successfully.");

        // Retrieve all users
        List<User> allUsers = userDao.getAllUser();
        System.out.println("All Users:");
        for (User user : allUsers) {
            System.out.println("User ID: " + user.getUserId() + ", Username: " + user.getUsername());
        }

//        // Delete the User
//        userDao.deleteUser(1);
//        System.out.println("User deleted successfully.");
    }
}
