package com.lzumetal.mybatispages.page;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.Future;

import static com.lzumetal.mybatispages.page.PageConfig.*;


public class Pagination<T> implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(Pagination.class);

    private int pageSize;
    private int currentPage;
    private long totalPage;
    private long totalCount;
    private boolean hasPrevious = false;
    private boolean hasNext = false;
    private List<T> data;

    public Pagination(int currentPage, int pageSize) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
    }

    public Pagination(int currentPage, int pageSize, long totalCount) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.totalCount = totalCount;
        this.totalPage = getTotalPageCount();
        refresh();
    }


    public Pagination(int currentPage, int pageSize, long totalCount, List<T> data) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.totalCount = totalCount;
        this.totalPage = getTotalPageCount();
        this.data = data;
        refresh();
    }


    /**
     * 取总页数
     */
    public final long getTotalPageCount() {
        if (totalCount % pageSize == 0)
            return totalCount / pageSize;
        else
            return totalCount / pageSize + 1;
    }

    /**
     * 刷新当前分页对象数据
     */
    public void refresh() {
        if (totalPage <= 1) {
            hasPrevious = false;
            hasNext = false;
        } else if (currentPage == 1) {
            hasPrevious = false;
            hasNext = true;
        } else if (currentPage == totalPage) {
            hasPrevious = true;
            hasNext = false;
        } else {
            hasPrevious = true;
            hasNext = true;
        }
    }

    public static Logger getLogger() {
        return logger;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public long getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(long totalPage) {
        this.totalPage = totalPage;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }


}
