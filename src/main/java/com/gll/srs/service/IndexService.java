package com.gll.srs.service;

import com.gll.srs.entity.Message;
import com.gll.srs.entity.Missingpersons;
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
}
