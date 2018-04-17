package com.lzumetal.mybatispages.page2;

public class PageParam {

    private int offset;//第几页
    private int limit;//每页限制条数
    private long total; //总条数，插件会回填这个值
    private long totalPage; //总页数，插件会回填这个值.

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public long getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(long totalPage) {
        this.totalPage = totalPage;
    }

    public final void caculateTotalPage() {
        if (total % limit == 0)
            this.totalPage = total / limit;
        else
            this.totalPage = total / limit + 1;
    }
}
