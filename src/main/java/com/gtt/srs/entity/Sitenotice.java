package com.gtt.srs.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Sitenotice {
    private int noticeId;
    private String noticeTitle;
    private String noticeContent;
    private String noticeDate;

    @Id
    @Column(name = "notice_id", nullable = false)
    public int getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(int noticeId) {
        this.noticeId = noticeId;
    }

    @Basic
    @Column(name = "notice_title", nullable = true, length = 100)
    public String getNoticeTitle() {
        return noticeTitle;
    }

    public void setNoticeTitle(String noticeTitle) {
        this.noticeTitle = noticeTitle;
    }

    @Basic
    @Column(name = "notice_content", nullable = true, length = 200)
    public String getNoticeContent() {
        return noticeContent;
    }

    public void setNoticeContent(String noticeContent) {
        this.noticeContent = noticeContent;
    }

    @Basic
    @Column(name = "notice_date", nullable = true, length = 20)
    public String getNoticeDate() {
        return noticeDate;
    }

    public void setNoticeDate(String noticeDate) {
        this.noticeDate = noticeDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sitenotice that = (Sitenotice) o;

        if (noticeId != that.noticeId) return false;
        if (noticeTitle != null ? !noticeTitle.equals(that.noticeTitle) : that.noticeTitle != null) return false;
        if (noticeContent != null ? !noticeContent.equals(that.noticeContent) : that.noticeContent != null)
            return false;
        if (noticeDate != null ? !noticeDate.equals(that.noticeDate) : that.noticeDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = noticeId;
        result = 31 * result + (noticeTitle != null ? noticeTitle.hashCode() : 0);
        result = 31 * result + (noticeContent != null ? noticeContent.hashCode() : 0);
        result = 31 * result + (noticeDate != null ? noticeDate.hashCode() : 0);
        return result;
    }
}
