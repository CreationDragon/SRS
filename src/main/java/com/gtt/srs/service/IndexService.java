package com.gtt.srs.service;

import com.gtt.srs.entity.Message;
import com.gtt.srs.entity.Missingpersons;
import com.gtt.srs.entity.Volunteer;
import com.gtt.srs.model.Area;
import com.gtt.srs.model.ThreeArea;
import com.gtt.srs.model.User;

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

    void putPersonsPic(String myFileName, Integer useriD);

    List<String> getPersonPics(Integer id);

    void updatePic(String myFileName, Integer integer);

    int getPersonsCount();

}
