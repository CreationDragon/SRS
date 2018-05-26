package com.gtt.srs.service.impl;

import com.gtt.srs.entity.Missingpersons;
import com.gtt.srs.entity.RecordHistory;
import com.gtt.srs.entity.User;
import com.gtt.srs.repository.AppRepository;
import com.gtt.srs.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AppServiceImpl implements AppService {
    private User user = new User();
    private List<RecordHistory> histories = new ArrayList<>();
    private List<Missingpersons> missingpersonsList = new ArrayList<>();
    @Autowired
    private AppRepository appRepository;

    @Override
    public User login(String userName, String userPsw) {
        user = appRepository.login(userName, userPsw);
        return user;
    }

    @Override
    public void setHistoricalRecords(Integer userId, Integer missPersonId) {
        Integer count = appRepository.queryHistoricalRecords(userId, missPersonId);
        if (count == 0) {
            appRepository.setHistoricalRecords(userId, missPersonId);
        }
    }

    @Override
    public List<Missingpersons> getRecordHistoryByUserId(Integer userId) {
        missingpersonsList = new ArrayList<>();

        List<Integer> missPersonsId = appRepository.queryMissPersionId(userId);

        for (Integer i : missPersonsId
                ) {
            Missingpersons missingpersons = new Missingpersons();
            missingpersons = appRepository.getMissPersonById(i);
            missingpersonsList.add(missingpersons);
        }

        return missingpersonsList;
    }
}
