package com.example.UserDao;

import com.example.Model.User;

import java.util.List;

public interface UserDao {
    void addUser(User user);
    User getUser(int userId);
    void updateUser(User user);
    void deleteUser(int userId);
    List<User> getAllUser();

    //Third one But in Interface this time
    
}
