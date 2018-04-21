package com.gll.srs.service;

import com.gll.srs.entity.Message;
import com.gll.srs.entity.Missingpersons;
import com.gll.srs.entity.Volunteer;
import com.gll.srs.model.Area;
import com.gll.srs.model.ThreeArea;
import com.gll.srs.model.User;

import java.util.List;

public interface IndexService {
    ThreeArea getAreaById(String provinceID, String cityID, String districtID);

    String registerNumber(User user);

    User getUserInfo();

    User login(String userName, String userPsw);

    Area getArea(String id, String sign);

    List<User> getAdminInfo();

    int putMessage(Message message, Integer userID);

    List<Missingpersons> infoSearch(String keyWord);

    int releaseMissInfo(Missingpersons missPersonsInfo, Integer userID);

    int volunteerRegister(Volunteer volunteer);

    List<Volunteer> getVolunteer();

    Volunteer getVolunteerInfo(Integer volunteerId);

    void putPersonsPic(String myFileName, String userID);

    List<String> getPersonPics(Integer id);

    void updatePic(String myFileName, Integer integer);

}
