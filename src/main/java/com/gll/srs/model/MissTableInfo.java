package com.gll.srs.model;

import com.gll.srs.model.Missingpersons;

import java.util.List;

public class MissTableInfo {
    private Integer code;
    private String msg;
    private int count;
    private List<Missingpersons> data;

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

    public List<Missingpersons> getData() {
        return data;
    }

    public void setData(List<Missingpersons> data) {
        this.data = data;
    }
}
