package ru.dz.quotewebapp.util;


import java.util.List;
import java.util.ArrayList;


public class PageRequestUtil {
    public final static String SORT_DIR_ASC = "asc";
    public final static String SORT_DIR_DESC = "desc";

    private int pageNumber;
    private int pageSize;

    private String sortBy;
    private String sortDir;


    public PageRequestUtil(int pageNumber, int pageSize) {
        this.setPagination(pageNumber, pageSize);
    }

    public PageRequestUtil(int pageNumber, int pageSize, String sortBy, String sortDir) {
        this.setPagination(pageNumber, pageSize);
        this.setSorting(sortBy, sortDir);
    }

    public void setPagination(int pageNumber, int pageSize) {
        if (pageNumber < 0) {
            throw new IllegalArgumentException("pageNumber must be greater or equal than 0");
        }

        if (pageSize < 0) {
            throw new IllegalArgumentException("pageSize must be greater or equal than 0");
        }

        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
    }

    public void setSorting(String sortBy, String sortDir) {
        if (!"".equals(sortDir)) {
            List<String> sortDirAllowedValues = new ArrayList<String>(2);
            sortDirAllowedValues.add(SORT_DIR_ASC);
            sortDirAllowedValues.add(SORT_DIR_DESC);

            if(!sortDirAllowedValues.contains(sortDir)){
                throw new IllegalArgumentException("sortBy must be equal asc or desc");
            }
        }

        this.sortBy = sortBy;
        this.sortDir = sortDir;
    }


    public int getPageNumber() {
        return pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public String getSortBy() {
        return sortBy;
    }

    public String getSortDir() {
        return sortDir;
    }
}
