package ru.dz.quotewebapp.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;

import ru.dz.quotewebapp.model.User;
import ru.dz.quotewebapp.util.PageRequestUtil;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Criteria getUserListCriteria() {
        Session session = this.sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(User.class);

        return criteria;
    }

    public void addUser(User user) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(user);
    }

    public User getUserByName(String userName) {
        Session session = this.sessionFactory.getCurrentSession();
        User user = (User) session.byNaturalId(User.class).using("userName", userName).load();

        return user;
    }

    public List<User> getUserList(PageRequestUtil pageRequestUtil) {
        final Criteria criteria = this.getUserListCriteria();

        /** Pagination */
        if (pageRequestUtil.getPageNumber() > 0 && pageRequestUtil.getPageSize() > 0) {
            criteria.setFirstResult((pageRequestUtil.getPageNumber() - 1) * pageRequestUtil.getPageSize());
            criteria.setMaxResults(pageRequestUtil.getPageSize());
        }

        /** Sort */
        if(!"".equals(pageRequestUtil.getSortBy()) && !"".equals(pageRequestUtil.getSortDir())){
            if (pageRequestUtil.getSortDir().equals(PageRequestUtil.SORT_DIR_DESC)) {
                criteria.addOrder(Order.desc(pageRequestUtil.getSortBy()));
            } else {
                criteria.addOrder(Order.asc(pageRequestUtil.getSortBy()));
            }
        }

        return (List<User>) criteria.list();
    }
}
