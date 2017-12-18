package com.myDemo.common.bean;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 基础分页面信息 <仿写的分页插件里面的>
   分页：页面请求只需要传入查询信息以及页码，和页面大小即可
 */
public class BasePageInfoList<E> extends ArrayList<E> {
    private PaginatorInfo paginatorInfo;
    public BasePageInfoList(){

    }
    public BasePageInfoList(Collection<? extends E> c) {
        super(c);
        this.paginatorInfo = paginatorInfo;
    }
    public BasePageInfoList(Collection<? extends E> c, PaginatorInfo paginatorInfo) {
        super(c);
        this.paginatorInfo = paginatorInfo;
    }

    public void setPaginatorInfo(PaginatorInfo paginatorInfo) {
        this.paginatorInfo = paginatorInfo;
    }

    public PaginatorInfo getPaginatorInfo(){
        return this.paginatorInfo;
    }

}
