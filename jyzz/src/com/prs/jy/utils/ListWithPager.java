package com.prs.jy.utils;

import java.util.List;

/**
 * 数据结构
 *
 * @author Ouyang
 * @param <T>
 */
public class ListWithPager<T> {
    private List<T> results;
    private Pager pager;

    public List<T> getResults() {
        return results;
    }

    public void setResults(List<T> results) {
        this.results = results;
    }

    public Pager getPager() {
        return pager;
    }

    public void setPager(Pager pager) {
        this.pager = pager;
    }

    public ListWithPager() {

    }

    public ListWithPager(List<T> results, Pager pager) {
        this.results = results;
        this.pager = pager;
    }

}
