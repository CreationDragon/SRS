package com.gtt.srs.repository;

import com.gtt.srs.entity.Missingpersons;
import com.gtt.srs.entity.RecordHistory;
import com.gtt.srs.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AppRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private User user = new User();
    private List<RecordHistory> histories = new ArrayList<>();

    public User login(String userName, String userPsw) {
        user = jdbcTemplate.queryForObject("SELECT * FROM srs.user WHERE user_name= ? AND user_psw=?", new Object[]{userName, userPsw}, new BeanPropertyRowMapper<>(User.class));
        return user;
    }

    public Integer setHistoricalRecords(Integer userId, Integer missPersonId) {
        Integer count = jdbcTemplate.update("INSERT INTO record_history(user_id,missperson_id)VALUES (?,?)", new Object[]{userId, missPersonId});
        return count;
    }

    public Integer queryHistoricalRecords(Integer userId, Integer missPersonId) {
        Integer count = jdbcTemplate.queryForObject("SELECT count(*) FROM record_history WHERE user_id= ? AND missperson_id= ?",
                new Object[]{userId, missPersonId}, Integer.class);
        return count;
    }

    public List<Integer> queryMissPersionId(Integer userId) {
        List<Integer> ids = jdbcTemplate.queryForList("SELECT missperson_id FROM record_history WHERE user_id=" + userId, Integer.class);
        return ids;
    }

    public Missingpersons getMissPersonById(Integer i) {

        Missingpersons missingpersons = new Missingpersons();
        missingpersons = jdbcTemplate.queryForObject("SELECT * FROM missingpersons WHERE persons_id=" + i, new BeanPropertyRowMapper<>(Missingpersons.class));
        List<String> picName = jdbcTemplate.queryForList("SELECT pic_name FROM personspic WHERE persons_id=" + i, String.class);
        if (picName.size() != 0) {
            missingpersons.setPsersonsPic(picName.get(0));
        }
        return missingpersons;
    }
}
