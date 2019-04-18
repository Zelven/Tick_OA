package com.qianfeng.utils;

import com.qianfeng.common.PageInfo;

import java.util.List;

public class PageUtil {

    /**
     *  依据页码与每页展示数量求Index的方法
     * @param page
     *  计算时需要的页码数据。
     * @param limit
     *  每页需要展示结果的条数，SQL语句中的limit。
     * @return
     *  返回的SQL语句所需的index.
     */
    public static int getIndex(Integer page, int limit){
        int index=0;
        if(page>0) {
            index = (page - 1) * limit;
        }
        return index;

    }

    public static PageInfo createPage(int count, List data) {

        PageInfo infos = new PageInfo();
        infos.setCount(count);
        infos.setData(data);
        return infos;

    }

}
