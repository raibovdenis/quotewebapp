package ru.dz.quotewebapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.dz.quotewebapp.dao.UserDao;
import ru.dz.quotewebapp.model.User;
import ru.dz.quotewebapp.util.PageRequestUtil;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;


    @Transactional
    public User getUserByName(String userName) {
        try {
            return this.userDao.getUserByName(userName);
        } catch (Exception e){
            throw new RuntimeException("Unable to get User by name. Error with message - " + e.getMessage());
        }
    }

    @Transactional
    public List<User> getUserList(PageRequestUtil pageRequestUtil){
        try {
            return this.userDao.getUserList(pageRequestUtil);
        } catch (Exception e){
            throw new RuntimeException("Unable to get User list. Error with message - " + e.getMessage());
        }
    }
}
