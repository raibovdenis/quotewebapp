package ru.dz.quotewebapp.dao;

import ru.dz.quotewebapp.model.Quote;
import ru.dz.quotewebapp.util.PageRequestUtil;
import ru.dz.quotewebapp.util.PageUtil;

public interface QuoteDao {

    public void addQuote(Quote quote);

    public PageUtil getQuoteListPage(PageRequestUtil pageRequestUtil);
}
