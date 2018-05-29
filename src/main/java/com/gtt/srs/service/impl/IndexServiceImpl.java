package com.gtt.srs.service.impl;

import com.gtt.srs.entity.Message;
import com.gtt.srs.entity.Missingpersons;
import com.gtt.srs.entity.Personspic;
import com.gtt.srs.entity.Volunteer;
import com.gtt.srs.model.Area;
import com.gtt.srs.model.ThreeArea;
import com.gtt.srs.model.User;
import com.gtt.srs.repository.IndexRepository;
import com.gtt.srs.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class IndexServiceImpl implements IndexService {
    @Autowired
    private IndexRepository indexRepository;
    private String msg;
    private User user;
    private Volunteer volunteer;
    private Integer userId = null;
    private List<User> userList = new ArrayList<>();
    private List<Missingpersons> missingpersonsList = new ArrayList<>();
    private List<Volunteer> volunteerList = new ArrayList<>();

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
        for (Missingpersons mps : missingpersonsList
                ) {
            List<String> picNames = indexRepository.getMissPersonsPicById(mps.getPersonsId());
            if (picNames.size() != 0) {
                mps.setPsersonsPic(picNames.get(0));
            }
        }
        return missingpersonsList;
    }

    @Override
    public int releaseMissInfo(Missingpersons missPersonsInfo, Integer userID) {
        int count = indexRepository.releaseMissInfo(missPersonsInfo, userID);
        return count;
    }

    @Override
    public int volunteerRegister(Volunteer volunteer) {

        Date date = new Date();

        SimpleDateFormat sf = new SimpleDateFormat();
        String time = sf.format(date);

        int value = indexRepository.volunteerRegister(volunteer, time);


        return value;
    }

    @Override
    public List<Volunteer> getVolunteer() {
        volunteerList = indexRepository.getVolunteer();
        return volunteerList;
    }

    @Override
    public Volunteer getVolunteerInfo(Integer volunteerId) {
        volunteer = new Volunteer();
        volunteer = indexRepository.getVolunteerInfo(volunteerId);
        return volunteer;
    }

    @Override
    public void putPersonsPic(String myFileName, Integer useriD, String hashCode) {
        indexRepository.putPersonsPic(myFileName, useriD, hashCode);
    }

    @Override
    public List<String> getPersonPics(Integer id) {
        List<String> pics = indexRepository.getPersonPics(id);
        return pics;
    }

    @Override
    public void updatePic(String myFileName, Integer integer) {
        indexRepository.updatePic(myFileName, integer);
    }

    @Override
    public int getPersonsCount() {
        int count = indexRepository.getPersonsCount();
        return count;
    }

    @Override
    public Integer getRegistersId() {
        userId = indexRepository.getRegistersId();
        return userId;
    }

    @Override
    public Map<Integer, String> getAllHashCode() {
        Map<Integer, String> maps = new HashMap<>();
        List<Personspic> pics = indexRepository.getAllHashCode();
        for (Personspic p : pics
                ) {
            maps.put(p.getPersonsId(), p.getPicHashcode());
        }
        return maps;
    }
}
