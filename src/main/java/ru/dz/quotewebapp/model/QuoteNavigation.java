package ru.dz.quotewebapp.model;

import java.util.ArrayList;
import java.util.List;

import ru.dz.quotewebapp.util.PageRequestUtil;

public class QuoteNavigation {

    public final List getSortByList() {
        List<String> list = new ArrayList<String>();
        list.add("createdAt");

        return list;
    }

    public final List getSortDirList() {
        List<String> list = new ArrayList<String>();
        list.add(PageRequestUtil.SORT_DIR_DESC);
        list.add(PageRequestUtil.SORT_DIR_ASC);

        return list;
    }

    public final List getPageSizeList() {
        List<Integer> list = new ArrayList<Integer>();
        list.add(5);
        list.add(10);
        list.add(15);

        return list;
    }
}
