package com.example.demomqtt;

import java.util.Map;

public interface UserService {
    Map<String, User> getAll();
    void addUser(User user);
    void deleteUserById(String id);
    void updateUserById(User user, String id);
    User getUserById(String id);
}
