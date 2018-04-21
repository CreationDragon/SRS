package com.gtt.srs.repository;

import com.gtt.srs.entity.Antifraudtips;
import com.gtt.srs.entity.News;
import com.gtt.srs.entity.Sitenotice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class NewsRepository {
    private News news = new News();
    private List<News> newsList = new ArrayList<>();
    private List<Sitenotice> sitenoticeList = new ArrayList<>();
    private List<Antifraudtips> antifraudtipsList = new ArrayList<>();
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<News> getSuccessCases(Integer type) {
        newsList = jdbcTemplate.query("SELECT * FROM news WHERE news_type=" + type, new BeanPropertyRowMapper<>(News.class));
        return newsList;
    }

    public News getSuccessCasesById(Integer caseId) {
        news = jdbcTemplate.queryForObject("SELECT * FROM news WHERE news_id=" + caseId + " AND news_type= 0", new BeanPropertyRowMapper<>(News.class));
        return news;
    }

    public List<News> getSiteNotice(Integer type) {
        newsList = jdbcTemplate.query("SELECT * FROM news WHERE news_type=" + type, new BeanPropertyRowMapper<>(News.class));
        return newsList;
    }

    public News getSiteNoticeById(Integer noticeId) {
        news = jdbcTemplate.queryForObject("SELECT * FROM news WHERE news_id=" + noticeId + " AND news_type=1", new BeanPropertyRowMapper<>(News.class));
        return news;
    }

    public List<News> getAntiFraudiTips(Integer type) {
        newsList = jdbcTemplate.query("SELECT * FROM news WHERE news_type=2", new BeanPropertyRowMapper<>(News.class));
        return newsList;
    }

    public News getAntiFraudTipsById(Integer tipsId) {
        news = jdbcTemplate.queryForObject("SELECT * FROM news WHERE news_id=" + tipsId + " AND news_type=2", new BeanPropertyRowMapper<>(News.class));
        return news;
    }
}
