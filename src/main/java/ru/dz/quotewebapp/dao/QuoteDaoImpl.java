package ru.dz.quotewebapp.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;

import ru.dz.quotewebapp.model.Quote;
import ru.dz.quotewebapp.util.PageRequestUtil;
import ru.dz.quotewebapp.util.PageUtil;

@Repository
public class QuoteDaoImpl implements QuoteDao {

    @Autowired
    private SessionFactory sessionFactory;

    public void addQuote(Quote q) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(q);
    }

    private Criteria getQuoteListCriteria() {
        Session session = this.sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Quote.class);
        
        return criteria;
    }

    private Long getQuoteListRowCount() {
        final Criteria criteria = this.getQuoteListCriteria();
        criteria.setProjection(Projections.rowCount());
        Long rowCount = (Long) criteria.uniqueResult();

        return rowCount;
    }

    @SuppressWarnings("unchecked")
    private List<Quote> getQuoteList(PageRequestUtil pageRequestUtil) {
        final Criteria criteria = this.getQuoteListCriteria();

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

        return (List<Quote>) criteria.list();
    }


    public PageUtil getQuoteListPage(PageRequestUtil pageRequestUtil) {
        List<Quote> listQuote = this.getQuoteList(pageRequestUtil);
        Long listQuoteRowCount = this.getQuoteListRowCount();

        PageUtil listQuotePage = new PageUtil(listQuote, pageRequestUtil.getPageNumber(), pageRequestUtil.getPageSize(), listQuoteRowCount);

        return listQuotePage;
    }
}
