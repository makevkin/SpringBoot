package web.dao;

import web.model.User;

import java.util.List;
public interface UserDao {
    void addUser(User user);
    List<User> getAllUsers();
    void deleteUser(int id);
    User getUserId(int id);

}