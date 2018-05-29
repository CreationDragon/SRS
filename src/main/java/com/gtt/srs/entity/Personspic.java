package com.gtt.srs.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Personspic {
    private int picId;
    private Integer personsId;
    private String picName;
    private String picHashcode;

    @Id
    @Column(name = "pic_id", nullable = false)
    public int getPicId() {
        return picId;
    }

    public void setPicId(int picId) {
        this.picId = picId;
    }

    @Basic
    @Column(name = "persons_id", nullable = true)
    public Integer getPersonsId() {
        return personsId;
    }

    public void setPersonsId(Integer personsId) {
        this.personsId = personsId;
    }

    @Basic
    @Column(name = "pic_name", nullable = true, length = 255)
    public String getPicName() {
        return picName;
    }

    public void setPicName(String picName) {
        this.picName = picName;
    }

    @Basic
    @Column(name = "pic_hashcode", nullable = true, length = 255)
    public String getPicHashcode() {
        return picHashcode;
    }

    public void setPicHashcode(String picHashcode) {
        this.picHashcode = picHashcode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Personspic that = (Personspic) o;
        return picId == that.picId &&
                Objects.equals(personsId, that.personsId) &&
                Objects.equals(picName, that.picName) &&
                Objects.equals(picHashcode, that.picHashcode);
    }

    @Override
    public int hashCode() {

        return Objects.hash(picId, personsId, picName, picHashcode);
    }
}
