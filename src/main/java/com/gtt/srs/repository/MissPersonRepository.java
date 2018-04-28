package com.gtt.srs.repository;

import com.gtt.srs.entity.Missingpersons;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MissPersonRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private Missingpersons missingpersons = new Missingpersons();
    private List<Missingpersons> missingpersonsList = new ArrayList<>();

    public List<Missingpersons> getMissPerson(Integer page, Integer limit) {
        missingpersonsList = jdbcTemplate.query("SELECT * FROM missingpersons limit ?,?", new Object[]{(page - 1) * limit, limit}, new RowMapper<Missingpersons>() {
            @Override
            public Missingpersons mapRow(ResultSet resultSet, int i) throws SQLException {
                missingpersons = new Missingpersons();
                missingpersons.setPersonsId(resultSet.getInt("persons_id"));
                missingpersons.setMissState(resultSet.getInt("miss_state"));
                missingpersons.setPersonsAddress(resultSet.getString("persons_address"));
                missingpersons.setPersonsAge(resultSet.getInt("persons_age"));
                missingpersons.setPersonsBodyheight(resultSet.getInt("persons_bodyheight"));
                missingpersons.setPersonsContact(resultSet.getString("persons_contact"));
                missingpersons.setPersonsDateDiscovered(resultSet.getString("persons_DateDiscovered"));
                missingpersons.setPersonsDna(resultSet.getInt("persons_DNA"));
                missingpersons.setPersonsDiscoverySites(resultSet.getString("persons_DiscoverySites"));
                missingpersons.setPersonsName(resultSet.getString("persons_name"));
                missingpersons.setPersonsNote(resultSet.getString("persons_note"));
                missingpersons.setPersonsReleasedate(resultSet.getString("persons_RELEASEDATE"));
                missingpersons.setPersonsRescueunit(resultSet.getString("persons_rescueunit"));
                missingpersons.setPersonsGender(resultSet.getString("persons_gender"));
                missingpersons.setPersonsFeature(resultSet.getString("persons_feature"));
                List<String> picName = jdbcTemplate.queryForList("SELECT pic_name FROM personspic WHERE persons_id=" + resultSet.getInt("persons_id"), String.class);
                if (picName.size() != 0) {
                    missingpersons.setPsersonsPic(picName.get(0));
                } else {
                    missingpersons.setPsersonsPic(null);
                }
                return missingpersons;
            }
        });
        return missingpersonsList;
    }

    public Missingpersons getMissPersonsById(Integer missPersonId) {
        missingpersons = jdbcTemplate.queryForObject("SELECT * FROM missingpersons WHERE persons_id=" + missPersonId, new BeanPropertyRowMapper<>(Missingpersons.class));
        return missingpersons;
    }

    public String getMissPersonsPic(Integer missPersonId) {
        String picName = jdbcTemplate.queryForObject("SELECT pic_name FROM personspic WHERE persons_id=" + missPersonId, String.class);

        return picName;
    }

    public Integer getMissPersonsCount() {
        Integer count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM missingpersons", Integer.class);
        return count;
    }
}
