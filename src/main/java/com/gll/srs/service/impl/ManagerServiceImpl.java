package com.gll.srs.service.impl;

import com.gll.srs.model.Message;
import com.gll.srs.entity.User;
import com.gll.srs.model.ThreeArea;
import com.gll.srs.repository.ManagerRepository;
import com.gll.srs.service.ManagerService;
import com.gll.srs.utils.EmailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ManagerServiceImpl implements ManagerService {
    private List<User> users = new ArrayList<>();
    private List<com.gll.srs.model.User> userList;
    private com.gll.srs.model.User user;
    private List<Message> messageList = new ArrayList<>();
    @Autowired
    private ManagerRepository managerRepository;

    @Override
    public List<com.gll.srs.model.User> getUser(Integer page, Integer limit) {
        users = managerRepository.getUser(page, limit);
        userList = new ArrayList<>();
        Integer count = null;

        for (User u : users
                ) {
            String city = managerRepository.getCityById(u.getUserCity());
            String province = managerRepository.getProvinceById(u.getUserProvince());
            String district = managerRepository.getDistrictById(u.getUserDistrict());
            user = new com.gll.srs.model.User();
            user.setUserId(u.getUserId());
            user.setUserName(u.getUserName());
            user.setUserAddressDetail(u.getUserAddressDetail());
            user.setUserAuthority(u.getUserAuthority());
            user.setCity(city);
            user.setProvince(province);
            user.setDistrict(district);
            user.setUserEmail(u.getUserEmail());
            user.setUserPhone(u.getUserPhone());
            user.setUserGener(u.getUserGener());
            user.setOption("<div><i class=\"layui-icon\" style=\"font-size: 30px;color: #009688\" id=\"edit\">&#xe642;</i></i><i class=\"layui-icon\"style=\"font-size: 30px;color: #009688\" id=\"del\">&#xe640;</i></div>");
            userList.add(user);
        }

        return userList;
    }

    @Override
    public Integer deleteUser(String userID) {
        Integer userid = Integer.parseInt(userID);
        Integer result = managerRepository.deleteUser(userid);
        return result;
    }

    @Override
    public com.gll.srs.model.User getUserInfoById(String userId) {
        Integer userid = Integer.parseInt(userId);
        com.gll.srs.model.User user = new com.gll.srs.model.User();
        user = managerRepository.getUserInfoById(userid);
        return user;
    }

    @Override
    public ThreeArea getAreaById(String provinceID, String cityID, String districtID) {
        ThreeArea threeArea = new ThreeArea();
        threeArea = managerRepository.getAreaById(provinceID, cityID, districtID);
        return threeArea;
    }

    @Override
    public String editUser(com.gll.srs.model.User user, String userID) {
        Integer userid = Integer.parseInt(userID);
        String msg = managerRepository.editUser(user, userid);
        return msg;
    }


    @Override
    public Integer deleteQues(String quesID) {

        Integer quesid = Integer.parseInt(quesID);
        Integer result = managerRepository.deleteQues(quesid);
        return result;
    }


    @Override
    public String editQues(String title, String answerA, String answerB, String answerC, String answerD, String quesID) {
        Integer quesid = Integer.parseInt(quesID);
        String msg = managerRepository.editQues(title, answerA, answerB, answerC, answerD, quesid);
        return msg;
    }

    @Override
    public List<Message> getMessage(String adminID) {
        Integer adminid = Integer.parseInt(adminID);
        messageList = managerRepository.getMessage(adminid);
        return messageList;
    }

    @Override
    public List<Message> getMessageById(String adminID, String messageID) {
        Integer adminid = Integer.parseInt(adminID);
        Integer messageid = Integer.parseInt(messageID);
        messageList = managerRepository.getMessageById(adminid, messageid);
        return messageList;
    }

    @Override
    public boolean replyUser(Integer messageID, String userEmail, String replyContent, String adminEmail, String userName) {
        boolean flag = false;

        Integer val = managerRepository.replyUser(messageID, userEmail, replyContent, adminEmail);

        if (val != 0) {
            try {
                EmailUtils.sendEmail(replyContent, userEmail, adminEmail, userName);
                flag = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return flag;
    }

    @Override
    public int getUserCount() {
        int count = managerRepository.getUserCount();
        return count;
    }


}
