package org.forten.si.dto;

import org.forten.utils.collection.CollectionUtil;
import org.forten.utils.system.PageInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by student1 on 2017/7/4.
 */
public class RoWithPages<T> {
    public static final RoWithPages EMPTY_RO = new RoWithPages(new ArrayList(0), PageInfo.EMPTY_PAGE);
    private List<T> list;
    private PageInfo page;

    public RoWithPages(List<T> list, PageInfo page) {
        this.list = list;
        this.page = page;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public PageInfo getPage() {
        return page;
    }

    public void setPage(PageInfo page) {
        this.page = page;
    }

    public boolean isEmptyData() {
        return CollectionUtil.isEmpty(list);
    }

    public int getListSize(){
        return CollectionUtil.isEmpty(list) ? 0: list.size();
    }

    @Override
    public String toString() {
        return "RoWithPages{" +
                "list=" + list +
                ", page=" + page +
                '}';
    }
}
