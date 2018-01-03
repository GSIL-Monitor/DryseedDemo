package com.dryseed.dryseedapp.practice.location.lib;


import java.util.List;

/**
 * Created by yaocheng on 2017/5/22.
 */

public class CityInfo {
    private String adcode = "";
    private String name = "";
    private String id = "";
    private List<CityInfo> list = null;

    public CityInfo getLastNode() {
        return lastNode;
    }

    public void setLastNode(CityInfo lastNode) {
        this.lastNode = lastNode;
    }

    private CityInfo lastNode = null;

    public String getAdcode() {
        return adcode;
    }

    public void setAdcode(String adcode) {
        this.adcode = adcode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<CityInfo> getList() {
        return list;
    }

    public void setList(List<CityInfo> list) {
        this.list = list;
    }
}
