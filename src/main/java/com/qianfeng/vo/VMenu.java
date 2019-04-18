package com.qianfeng.vo;

import com.qianfeng.entity.Authority;

import java.util.List;

/**
 * @author Zelven
 * @date 2019/4/13/013
 */
public class VMenu {


    private Integer id;
    private String title;
    private String url;
    // 子菜单
    private List<VMenu> childs;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }

    public List<VMenu> getChilds() {
        return childs;
    }

    public void setChilds(List<VMenu> childs) {
        this.childs = childs;
    }

    @Override
    public String toString() {
        return "VMenu{" +
                "id=" + id +
                ", menuName='" + title + '\'' +
                ", url='" + url + '\'' +
                ", menuList=" + childs +
                '}';
    }
}
