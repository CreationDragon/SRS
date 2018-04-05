package com.gll.srs.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Antifraudtips {
    private int tipsId;
    private String tipsTitle;
    private String tipsContent;
    private String tipsDate;

    @Id
    @Column(name = "tips_id", nullable = false)
    public int getTipsId() {
        return tipsId;
    }

    public void setTipsId(int tipsId) {
        this.tipsId = tipsId;
    }

    @Basic
    @Column(name = "tips_title", nullable = true, length = 100)
    public String getTipsTitle() {
        return tipsTitle;
    }

    public void setTipsTitle(String tipsTitle) {
        this.tipsTitle = tipsTitle;
    }

    @Basic
    @Column(name = "tips_content", nullable = true, length = 200)
    public String getTipsContent() {
        return tipsContent;
    }

    public void setTipsContent(String tipsContent) {
        this.tipsContent = tipsContent;
    }

    @Basic
    @Column(name = "tips_date", nullable = true, length = 20)
    public String getTipsDate() {
        return tipsDate;
    }

    public void setTipsDate(String tipsDate) {
        this.tipsDate = tipsDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Antifraudtips that = (Antifraudtips) o;

        if (tipsId != that.tipsId) return false;
        if (tipsTitle != null ? !tipsTitle.equals(that.tipsTitle) : that.tipsTitle != null) return false;
        if (tipsContent != null ? !tipsContent.equals(that.tipsContent) : that.tipsContent != null) return false;
        if (tipsDate != null ? !tipsDate.equals(that.tipsDate) : that.tipsDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = tipsId;
        result = 31 * result + (tipsTitle != null ? tipsTitle.hashCode() : 0);
        result = 31 * result + (tipsContent != null ? tipsContent.hashCode() : 0);
        result = 31 * result + (tipsDate != null ? tipsDate.hashCode() : 0);
        return result;
    }
}
