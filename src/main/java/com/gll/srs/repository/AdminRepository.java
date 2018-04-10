package com.gll.srs.repository;

import com.gll.srs.entity.Missingpersons;
import com.gll.srs.entity.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AdminRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    List<News> newsList = new ArrayList<>();
    private List<Missingpersons> missingpersonsList = new ArrayList<>();

    public List<Missingpersons> getMissPersonsInfo() {
        missingpersonsList = jdbcTemplate.query("SELECT * FROM missingpersons WHERE state = 0", new BeanPropertyRowMapper<>(Missingpersons.class));
        return missingpersonsList;
    }

    public Integer deleteMissInfo(Integer missInfoId) {
        Integer result = jdbcTemplate.update("UPDATE missingpersons SET state=1 WHERE persons_id=?", new Object[]{missInfoId});
        return result;
    }

    public Missingpersons getMissPersonsInfoById(Integer missInfoId) {
        Missingpersons missingpersons = new Missingpersons();
        missingpersons = jdbcTemplate.queryForObject("SELECT * FROM missingpersons WHERE state = 0 AND persons_id = " + missInfoId, new BeanPropertyRowMapper<>(Missingpersons.class));
        return missingpersons;
    }

    public int UpdateMissInfoById(Integer missInfoId, Missingpersons missingpersons) {
        int value = jdbcTemplate.update("UPDATE missingpersons SET persons_address=?,persons_age=?,persons_bodyheight=?," +
                        "persons_Contact=?,persons_DNA=?,persons_dress=?,persons_feature=?,persons_gender=?,persons_name=?,persons_RELEASEDATE=? WHERE persons_id=?",
                new Object[]{missingpersons.getPersonsAddress(), missingpersons.getPersonsAge(), missingpersons.getPersonsBodyheight(), missingpersons.getPersonsContact(),
                        missingpersons.getPersonsDna(), missingpersons.getPersonsDress(), missingpersons.getPersonsFeature(), missingpersons.getPersonsGender(), missingpersons.getPersonsName(),
                        missingpersons.getPersonsReleasedate(), missInfoId});
        return value;
    }

    public Integer deleteMessage(Integer messageID) {
        Integer value = jdbcTemplate.update("UPDATE message SET reply_state=1 WHERE message_id=" + messageID);
        return value;
    }

    public List<News> getNews() {
        newsList = new ArrayList<>();
        newsList = jdbcTemplate.query("SELECT * FROM news WHERE news_state = 0", new BeanPropertyRowMapper<>(News.class));
        return newsList;
    }

    public Integer deleteNews(Integer newsId) {
        Integer value = jdbcTemplate.update("UPDATE news SET news_state = 1 WHERE news_id=" + newsId);
        return value;
    }

    public News getNewsById(Integer newsId) {
        News news = new News();

        news = jdbcTemplate.queryForObject("SELECT * FROM news WHERE news_id = " + newsId, new BeanPropertyRowMapper<>(News.class));

        return news;
    }
}
