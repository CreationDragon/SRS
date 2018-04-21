package com.gtt.srs.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Personspic {
    private int picId;
    private Integer personsId;
    private String picName;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Personspic that = (Personspic) o;

        if (picId != that.picId) return false;
        if (personsId != null ? !personsId.equals(that.personsId) : that.personsId != null) return false;
        if (picName != null ? !picName.equals(that.picName) : that.picName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = picId;
        result = 31 * result + (personsId != null ? personsId.hashCode() : 0);
        result = 31 * result + (picName != null ? picName.hashCode() : 0);
        return result;
    }
}
