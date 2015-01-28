package ru.dz.quotewebapp.service;

import ru.dz.quotewebapp.model.Quote;
import ru.dz.quotewebapp.util.PageRequestUtil;
import ru.dz.quotewebapp.util.PageUtil;

public interface QuoteService {

    public void addQuote(Quote quote);

    public PageUtil getQuoteListPage(PageRequestUtil pageRequestUtil);
}
