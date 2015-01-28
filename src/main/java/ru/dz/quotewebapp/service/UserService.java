package ru.dz.quotewebapp.service;

import ru.dz.quotewebapp.model.User;
import ru.dz.quotewebapp.util.PageRequestUtil;

import java.util.List;

public interface UserService {

    public User getUserByName(String userName);

    public List<User> getUserList(PageRequestUtil pageRequestUtil);
}
