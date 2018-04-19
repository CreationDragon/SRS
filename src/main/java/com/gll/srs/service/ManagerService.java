package com.gll.srs.service;

import com.gll.srs.model.Message;
import com.gll.srs.model.ThreeArea;
import com.gll.srs.model.User;

import java.util.List;

public interface ManagerService {
    List<User> getUser(Integer page, Integer limit);

    Integer deleteUser(String userID);

    User getUserInfoById(String userId);

    ThreeArea getAreaById(String provinceID, String cityID, String districtID);

    String editUser(User user, String userID);

    Integer deleteQues(String quesID);

    String editQues(String title, String answerA, String answerB, String answerC, String answerD, String quesID);

    List<Message> getMessage(String adminID);

    List<Message> getMessageById(String adminID, String messageID);

    boolean replyUser(Integer messageID, String userEmail, String replyContent, String adminEmail, String userName);

    int getUserCount();

}
