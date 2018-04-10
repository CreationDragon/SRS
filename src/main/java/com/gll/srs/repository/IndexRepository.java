package com.gll.srs.repository;

import com.gll.srs.entity.Message;
import com.gll.srs.entity.Missingpersons;
import com.gll.srs.entity.Volunteer;
import com.gll.srs.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class IndexRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private List<AreaCity> areaCityList;
    private String msg;
    private User user;
    private Volunteer volunteer;
    private List<AreaProvince> areaProvinceList;
    private List<AreaDistrict> areaDistrictList;
    private List<User> userList = new ArrayList<>();
    private List<String> picNames = new ArrayList<>();
    private List<Volunteer> volunteerList = new ArrayList<>();
    private List<Missingpersons> missingpersonsList = new ArrayList<>();

    public Area getArea(Integer count, String sign) {
        if ("province".equals(sign)) {
            areaProvinceList = jdbcTemplate.query("select * from area_province", new BeanPropertyRowMapper<>(AreaProvince.class));
        } else if (sign.equals("city")) {
            areaCityList = jdbcTemplate.query("select * from qms.area_city WHERE province_id=" + count, new BeanPropertyRowMapper<>(AreaCity.class));
//            areaCityList = jdbcTemplate.query("select * from qms.area_city WHERE city_id=" + count, new BeanPropertyRowMapper<>(AreaCity.class));
        } else if (sign.equals("district")) {
//            count = count - 1;
            areaDistrictList = jdbcTemplate.query("select * from qms.area_district WHERE city_id=" + count, new BeanPropertyRowMapper<>(AreaDistrict.class));
//            areaDistrictList = jdbcTemplate.query("select * from qms.area_district WHERE district_id=" + count, new BeanPropertyRowMapper<>(AreaDistrict.class));
        }
        Area area = new Area();
        area.setProvinceList(areaProvinceList);
        area.setCityList(areaCityList);
        area.setDistrictList(areaDistrictList);

        return area;
    }

    public String registerNumber(com.gll.srs.model.User user) {
        Integer name = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM USER WHERE user_name = '" + user.getUserName() + "'", Integer.class);
        Integer email = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM USER WHERE user_email = '" + user.getUserEmail() + "'", Integer.class);
        Integer phone = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM USER WHERE user_phone = '" + user.getUserPhone() + "'", Integer.class);

        if (name != 0) {
            msg = "该昵称已被使用";
        } else if (email != 0) {
            msg = "该邮箱已被使用";
        } else if (phone != 0) {
            msg = "该电话已被使用";
        } else {
            int value = jdbcTemplate.update("INSERT INTO USER(user_name, user_psw, user_gener, user_phone, user_email,user_address_detail,user_province, user_city, user_district) VALUES(?,?,?,?,?,?,?,?,?) ",
                    new Object[]{
                            user.getUserName(), user.getUserPsw(), user.getUserGener(),
                            user.getUserPhone(), user.getUserEmail(), user.getUserAddressDetail(),
//                            user.getProvince(), user.getCity(), user.getDistrict()
                            user.getProvince(), user.getCity(), user.getDistrict()
                    });
            System.out.println("插入数据后返回的数是:     " + value);
            msg = "注册成功";
        }
        return msg;
    }

    public User getUserInfo() {
        return user;
    }

    public User login(String userName, String userPsw) {
        user = new User();
        try {
            user = jdbcTemplate.queryForObject("SELECT * FROM USER WHERE user_name= ? AND user_psw= ?", new Object[]{userName, userPsw}, new BeanPropertyRowMapper<>(User.class));
        } catch (Exception e) {
            return user;
        }
        return user;
    }

    public ThreeArea getAreaById(String provinceID, String cityID, String districtID) {
        com.gll.srs.entity.AreaProvince areaProvince = jdbcTemplate.queryForObject("SELECT * FROM qms.area_province WHERE province_id= ? ", new Object[]{provinceID}, new BeanPropertyRowMapper<>(com.gll.srs.entity.AreaProvince.class));
        com.gll.srs.entity.AreaCity areaCity = jdbcTemplate.queryForObject("SELECT * FROM qms.area_city WHERE city_id= ? ", new Object[]{cityID}, new BeanPropertyRowMapper<>(com.gll.srs.entity.AreaCity.class));
        com.gll.srs.entity.AreaDistrict areaDistrict = jdbcTemplate.queryForObject("SELECT * FROM qms.area_district WHERE district_id= ? ", new Object[]{districtID}, new BeanPropertyRowMapper<>(com.gll.srs.entity.AreaDistrict.class));

        ThreeArea threeArea = new ThreeArea();
        threeArea.setAreaProvince(areaProvince);
        threeArea.setAreaCity(areaCity);
        threeArea.setAreaDistrict(areaDistrict);

        return threeArea;
    }


    public List<User> getAdminInfo() {
        userList = new ArrayList<>();
        userList = jdbcTemplate.query("SELECT * FROM USER WHERE user_authority = '1'", new BeanPropertyRowMapper<>(User.class));

        return userList;
    }

    public int putMessage(Message message, Integer userID) {
        int value = jdbcTemplate.update("INSERT INTO message(message_content, admin_id,user_id) VALUES(?,?,?) ",
                new Object[]{
                        message.getMessageContent(), message.getAdminId(), userID
                });
        return value;
    }

    public List<Missingpersons> infoSearch(String keyWord) {
        missingpersonsList = new ArrayList<>();
        missingpersonsList = jdbcTemplate.query("SELECT * FROM missingpersons WHERE persons_name LIKE '%" + keyWord + "%'", new BeanPropertyRowMapper<>(Missingpersons.class));

        return missingpersonsList;
    }

    public int releaseMissInfo(Missingpersons missPersonsInfo, Integer userID) {
        int value = jdbcTemplate.update("INSERT INTO missingpersons(" +
                        "persons_name, persons_age, persons_bodyheight, persons_feature, persons_address, persons_DNA, " +
                        "persons_DateDiscovered, persons_DiscoverySites, persons_Contact, persons_rescueunit, persons_RELEASEDATE, " +
                        "persons_Note, user_id, miss_state, persons_gender,persons_dress) " +
                        "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ",
                new Object[]{missPersonsInfo.getPersonsName(), missPersonsInfo.getPersonsAge(), missPersonsInfo.getPersonsBodyheight(),
                        missPersonsInfo.getPersonsFeature(), missPersonsInfo.getPersonsAddress(), missPersonsInfo.getPersonsDna(), missPersonsInfo.getPersonsDateDiscovered(),
                        missPersonsInfo.getPersonsDiscoverySites(), missPersonsInfo.getPersonsContact(),
                        missPersonsInfo.getPersonsRescueunit(), missPersonsInfo.getPersonsReleasedate(), missPersonsInfo.getPersonsNote(), userID,
                        missPersonsInfo.getMissState(), missPersonsInfo.getPersonsGender(), missPersonsInfo.getPersonsDress()
                });
        return value;
    }

    public int volunteerRegister(Volunteer volunteer, String time) {
        int value = jdbcTemplate.update("INSERT INTO volunteer(volunteer_name,volunteer_age,volunteer_gender,volunteer_address,volunteer_email,volunteer_phone,volunteer_register_date) VALUES(?,?,?,?,?,?,?)",
                new Object[]{volunteer.getVolunteerName(), volunteer.getVolunteerAge(), volunteer.getVolunteerGender(), volunteer.getVolunteerAddress(), volunteer.getVolunteerEmail(), volunteer.getVolunteerPhone(), time});
        return value;
    }

    public List<Volunteer> getVolunteer() {
        volunteerList = jdbcTemplate.query("SELECT * FROM volunteer", new BeanPropertyRowMapper<>(Volunteer.class));
        return volunteerList;
    }

    public Volunteer getVolunteerInfo(Integer volunteerId) {
        volunteer = new Volunteer();

        volunteer = jdbcTemplate.queryForObject("SELECT * FROM volunteer WHERE volunteer_id = '" + volunteerId + "'", new BeanPropertyRowMapper<>(Volunteer.class));

        return volunteer;
    }

    public void putPersonsPic(String myFileName, Integer userid) {
        List<String> picNameList = jdbcTemplate.queryForList("SELECT pic_name FROM `personspic` WHERE persons_id=" + userid, String.class);
        if (picNameList.size() != 0) {
            for (String picName : picNameList
                    ) {
                if (picName == myFileName) {
                    System.out.println("已经有这张图片了");
                } else {
                    jdbcTemplate.update("INSERT INTO personspic(persons_id, pic_name)  VALUE (?,?)", new Object[]{userid, myFileName});
                }
            }
        } else {
            jdbcTemplate.update("INSERT INTO personspic(persons_id, pic_name)  VALUE (?,?)", new Object[]{userid, myFileName});
        }
    }

    public List<String> getPersonPics(Integer id) {
        picNames = new ArrayList<>();
        picNames = jdbcTemplate.queryForList("SELECT pic_name FROM personspic WHERE persons_id=" + id, String.class);
        return picNames;
    }
}
