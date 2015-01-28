package ru.dz.quotewebapp.dao;

import ru.dz.quotewebapp.model.User;
import ru.dz.quotewebapp.util.PageRequestUtil;

import java.util.List;

public interface UserDao {

    public void addUser(User user);

    public User getUserByName(String userName);

    public List<User> getUserList(PageRequestUtil pageRequestUtil);
}
