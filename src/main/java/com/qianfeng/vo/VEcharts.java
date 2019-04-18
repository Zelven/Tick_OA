package com.qianfeng.vo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Zelven
 * @date 2019/4/16/016
 */
public class VEcharts {

    private String id;
    private List<String> datax;
    private List<String> datas1;
    private List<String> datas2;
    private List<String> datas3;

    public VEcharts() {
        datax=new ArrayList<>();
        datas1=new ArrayList<>();
        datas2=new ArrayList<>();
        datas3=new ArrayList<>();
    }

    public List<String> getDatas3() {
        return datas3;
    }

    public void setDatas3(List<String> datas3) {
        this.datas3 = datas3;
    }

    public List<String> getDatas2() {
        return datas2;
    }

    public void setDatas2(List<String> datas2) {
        this.datas2 = datas2;
    }

    public List<String> getDatas1() {
        return datas1;
    }

    public void setDatas1(List<String> datas1) {
        this.datas1 = datas1;
    }

    public List<String> getDatax() {
        return datax;
    }

    public void setDatax(List<String> datax) {
        this.datax = datax;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
