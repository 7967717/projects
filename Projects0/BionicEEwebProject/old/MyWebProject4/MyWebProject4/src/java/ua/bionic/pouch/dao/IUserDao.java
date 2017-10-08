package ua.bionic.pouch.dao;

import ua.bionic.pouch.beans.User;
import java.util.List;

public interface IUserDao {
    void create(User user);
    User read(String login);
    boolean checkLogin(String login, String password);
    void update(User user);
    void delete(User user);
    List<User> findAll();
}
