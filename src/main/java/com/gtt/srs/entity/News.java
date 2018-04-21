package com.gtt.srs.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class News {
    private int newsId;
    private String newsTitle;
    private String newsContent;
    private String newsDate;
    private Integer newsType;
    private Integer newsState;

    @Id
    @Column(name = "news_id", nullable = false)
    public int getNewsId() {
        return newsId;
    }

    public void setNewsId(int newsId) {
        this.newsId = newsId;
    }

    @Basic
    @Column(name = "news_title", nullable = true, length = 255)
    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    @Basic
    @Column(name = "news_content", nullable = true, length = 255)
    public String getNewsContent() {
        return newsContent;
    }

    public void setNewsContent(String newsContent) {
        this.newsContent = newsContent;
    }

    @Basic
    @Column(name = "news_date", nullable = true, length = 50)
    public String getNewsDate() {
        return newsDate;
    }

    public void setNewsDate(String newsDate) {
        this.newsDate = newsDate;
    }

    @Basic
    @Column(name = "news_type", nullable = true)
    public Integer getNewsType() {
        return newsType;
    }

    public void setNewsType(Integer newsType) {
        this.newsType = newsType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        News news = (News) o;

        if (newsId != news.newsId) return false;
        if (newsTitle != null ? !newsTitle.equals(news.newsTitle) : news.newsTitle != null) return false;
        if (newsContent != null ? !newsContent.equals(news.newsContent) : news.newsContent != null) return false;
        if (newsDate != null ? !newsDate.equals(news.newsDate) : news.newsDate != null) return false;
        if (newsType != null ? !newsType.equals(news.newsType) : news.newsType != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = newsId;
        result = 31 * result + (newsTitle != null ? newsTitle.hashCode() : 0);
        result = 31 * result + (newsContent != null ? newsContent.hashCode() : 0);
        result = 31 * result + (newsDate != null ? newsDate.hashCode() : 0);
        result = 31 * result + (newsType != null ? newsType.hashCode() : 0);
        return result;
    }

    @Basic
    @Column(name = "news_state", nullable = true)
    public Integer getNewsState() {
        return newsState;
    }

    public void setNewsState(Integer newsState) {
        this.newsState = newsState;
    }
}
