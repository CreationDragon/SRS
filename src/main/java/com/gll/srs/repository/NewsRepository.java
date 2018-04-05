package com.gll.srs.repository;

import com.gll.srs.entity.Antifraudtips;
import com.gll.srs.entity.Sitenotice;
import com.gll.srs.entity.Successcase;
import com.gll.srs.model.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class NewsRepository {
    private Successcase successcase = new Successcase();
    private Sitenotice sitenotice = new Sitenotice();
    private Antifraudtips antifraudtips = new Antifraudtips();
    private List<Successcase> successcaseList = new ArrayList<>();
    private List<Sitenotice> sitenoticeList = new ArrayList<>();
    private List<Antifraudtips> antifraudtipsList = new ArrayList<>();
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Successcase> getSuccessCases() {
        successcaseList = jdbcTemplate.query("SELECT * FROM successcase", new BeanPropertyRowMapper<>(Successcase.class));
        return successcaseList;
    }

    public Successcase getSuccessCasesById(Integer caseId) {
        successcase = jdbcTemplate.queryForObject("SELECT * FROM successcase WHERE case_id=" + caseId, new BeanPropertyRowMapper<>(Successcase.class));
        return successcase;
    }

    public List<Sitenotice> getSiteNotice() {
        sitenoticeList = jdbcTemplate.query("SELECT * FROM sitenotice", new BeanPropertyRowMapper<>(Sitenotice.class));
        return sitenoticeList;
    }

    public Sitenotice getSiteNoticeById(Integer noticeId) {
        sitenotice = jdbcTemplate.queryForObject("SELECT * FROM sitenotice WHERE notice_id=" + noticeId, new BeanPropertyRowMapper<>(Sitenotice.class));
        return sitenotice;
    }

    public List<Antifraudtips> getAntiFraudiTips() {
        antifraudtipsList = jdbcTemplate.query("SELECT * FROM antifraudtips", new BeanPropertyRowMapper<>(Antifraudtips.class));
        return antifraudtipsList;
    }

    public Antifraudtips getAntiFraudTipsById(Integer tipsId) {
        antifraudtips = jdbcTemplate.queryForObject("SELECT * FROM antifraudtips WHERE tips_id=" + tipsId, new BeanPropertyRowMapper<>(Antifraudtips.class));
        return antifraudtips;
    }
}
