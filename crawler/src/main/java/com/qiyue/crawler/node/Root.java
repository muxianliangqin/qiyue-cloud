package com.qiyue.crawler.node;

public class Root implements Element {
    private String id;

    private String supId;

    public Root(){

    }

    public Root(String id, String supId) {
        this.id = id;
        this.supId = supId;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getSupId() {
        return supId;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setSupId(String supId) {
        this.supId = supId;
    }

    @Override
    public String toString() {
        return "{" +
                "'id':'" + id + '\'' +
                ", 'supId':'" + supId + '\'' +
                '}';
    }
}
