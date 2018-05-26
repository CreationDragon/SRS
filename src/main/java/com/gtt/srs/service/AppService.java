package com.gtt.srs.service;

import com.gtt.srs.entity.Missingpersons;
import com.gtt.srs.entity.User;

import java.util.List;

public interface AppService {
    User login(String userName, String userPsw);

    void setHistoricalRecords(Integer userId, Integer missPersonId);

    List<Missingpersons> getRecordHistoryByUserId(Integer userId);
}
