package com.gtt.srs.model;

import java.util.List;

public class NewsTableInfo {
    private Integer code;
    private String msg;
    private int count;
    private List<com.gtt.srs.model.News> data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<com.gtt.srs.model.News> getData() {
        return data;
    }

    public void setData(List<com.gtt.srs.model.News> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "NewsTableInfo{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", count=" + count +
                ", data=" + data +
                '}';
    }
}
