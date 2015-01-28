package ru.dz.quotewebapp.util;

import java.util.Iterator;
import java.util.List;

public class PageUtil<Entity> implements Iterable<Entity> {

    private List<Entity> content;

    private int pageNumber;

    private int pageSize;

    private long totalNumberOfElements;

    public PageUtil(List<Entity> content) {

        this.content = content;
    }

    public PageUtil(List<Entity> content, int pageNumber, int pageSize, long totalNumberOfElements) {

        this.content = content;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.totalNumberOfElements = totalNumberOfElements;
    }

    public List<Entity> getContent() {

        return content;
    }

    public int getPageNumber() {

        return pageNumber;
    }

    public int getNumberOfElements() {

        return content.size();
    }

    public int getPageSize() {

        return pageSize;
    }

    public long getTotalNumberOfElements() {

        return totalNumberOfElements;
    }

    public int getTotalPages() {

        if (this.getTotalNumberOfElements() == 0) {
            return 0;
        }

        if (this.getPageSize() == 0) {
            return 1;
        }

        int totalPages = (int) (this.getTotalNumberOfElements() / this.getPageSize());
        if (this.getTotalNumberOfElements() % this.getPageSize() > 0) {
            totalPages++;
        }

        return totalPages;
    }

    public boolean hasNextPage() {

        return (this.getPageNumber() < this.getTotalPages());
    }

    public boolean hasPreviousPage() {

        return (this.getPageNumber() > 1);
    }

    public boolean isFirstPage() {

        return (this.getPageNumber() == 1);
    }

    public boolean isLastPage() {

        return (this.getPageNumber() == this.getTotalPages());
    }

    public Iterator<Entity> iterator() {

        return content.iterator();
    }
}
