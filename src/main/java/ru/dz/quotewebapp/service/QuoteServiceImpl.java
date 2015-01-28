package ru.dz.quotewebapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.dz.quotewebapp.dao.QuoteDao;
import ru.dz.quotewebapp.dao.UserDao;
import ru.dz.quotewebapp.model.Quote;
import ru.dz.quotewebapp.model.User;
import ru.dz.quotewebapp.util.PageRequestUtil;
import ru.dz.quotewebapp.util.PageUtil;

@Service
public class QuoteServiceImpl implements QuoteService {

    @Autowired
    private QuoteDao quoteDao;

    @Autowired
    private UserDao userDao;

    @Transactional
    public void addQuote(Quote quote) {

        try {
            User oldUser = this.userDao.getUserByName(quote.getUser().getUserName());
            if(oldUser == null){
                this.userDao.addUser(quote.getUser());
                oldUser = this.userDao.getUserByName(quote.getUser().getUserName());
            }

            quote.setUser(oldUser);

            this.quoteDao.addQuote(quote);
        } catch(Exception e){
            throw new RuntimeException("Unable to create Quote. Error with message - " + e.getMessage());
        }

    }

    @Transactional
    public PageUtil getQuoteListPage(PageRequestUtil pageRequestUtil){
        try {
            return this.quoteDao.getQuoteListPage(pageRequestUtil);
        } catch (Exception e){
            throw new RuntimeException("Unable to get Quote list. Error with message - " + e.getMessage());
        }
    }

}
