package com.myDemo.common.bean;

import lombok.*;

/**
 * 页面信息
 */
@ToString
@Setter
public class PaginatorInfo {
    private int limit; //页面大小
    private int totalpage; //总页码
    private int currentpage;//当前页码
    private int currentResult;//当前开始记录数。 （limit的一个参数）
    private int totalCount; //总的记录数

    public int getLimit() {
        return limit;
    }

    public int getCurrentpage() {
        return currentpage;
    }

    public int getTotalCount() {
        return totalCount;
    }

    /**
     * 默认当前页面是1
     */
    public PaginatorInfo(){
        this.currentpage = 1;
    }
    public PaginatorInfo( int currentpage) {
        this.currentpage = currentpage;
    }
    public PaginatorInfo(int limit, int currentpage) {
        this.limit = limit;
        this.currentpage = currentpage;
    }

    public PaginatorInfo(int limit, int currentpage, int totalCount) {
        this.limit = limit;
        this.currentpage = currentpage;
        this.totalCount = totalCount;
    }

    /**
     * 获取分页的初始数据
     * @return
     */
    public int getCurrentResult() {
        if (this.currentpage <= 0) {
            this.currentResult=0;
        } else if (this.limit <= 0) {
            this.currentResult= 0;
        } else {
            int count = (this.currentpage-1)* this.limit;
            this.currentResult= count;
        }
        return this.currentResult;
    }

    /**
     * 获取总的页数
     * @return
     */
    public int getTotalpage() {
        if (this.totalCount <= 0) {
           this.totalpage=0;
        } else if (this.limit <= 0) {
            this.totalpage=0;
        } else {
            int count = this.totalCount / this.limit;
            if (this.totalCount % this.limit > 0) {
                ++count;
            }
            this.totalpage=count;
        }
        return this.totalpage;
    }


}
