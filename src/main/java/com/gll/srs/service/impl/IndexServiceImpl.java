package com.gll.srs.service.impl;

import com.gll.srs.entity.Message;
import com.gll.srs.entity.Missingpersons;
import com.gll.srs.model.Area;
import com.gll.srs.model.ThreeArea;
import com.gll.srs.model.User;
import com.gll.srs.repository.IndexRepository;
import com.gll.srs.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IndexServiceImpl implements IndexService {
    @Autowired
    private IndexRepository indexRepository;
    private String msg;
    private User user;
    private List<User> userList = new ArrayList<>();
    private List<Missingpersons> missingpersonsList = new ArrayList<>();

    @Override
    public ThreeArea getAreaById(String provinceID, String cityID, String districtID) {
        ThreeArea threeArea = new ThreeArea();
        threeArea = indexRepository.getAreaById(provinceID, cityID, districtID);
        return threeArea;
    }

    @Override
    public String registerNumber(User user) {
        msg = indexRepository.registerNumber(user);
        return msg;
    }

    @Override
    public User getUserInfo() {
        user = indexRepository.getUserInfo();
        return user;
    }

    @Override
    public User login(String userName, String userPsw) {
        user = indexRepository.login(userName, userPsw);
        return user;
    }

    @Override
    public Area getArea(String id, String sign) {
        Integer count = null;
        if ("" == id) {
            count = null;
        } else {
//            count = Integer.parseInt(id) + 1;
            count = Integer.parseInt(id);
        }
        Area area = indexRepository.getArea(count, sign);
        return area;
    }

    @Override
    public List<User> getAdminInfo() {
        userList = indexRepository.getAdminInfo();
        return userList;
    }

    @Override
    public int putMessage(Message message, Integer userID) {
        int count = indexRepository.putMessage(message, userID);
        return count;
    }

    @Override
    public List<Missingpersons> infoSearch(String keyWord) {
        missingpersonsList = new ArrayList<>();
        missingpersonsList = indexRepository.infoSearch(keyWord);
        return missingpersonsList;
    }

    @Override
    public int releaseMissInfo(Missingpersons missPersonsInfo, Integer userID) {
        int count = indexRepository.releaseMissInfo(missPersonsInfo, userID);
        return count;
    }
}
