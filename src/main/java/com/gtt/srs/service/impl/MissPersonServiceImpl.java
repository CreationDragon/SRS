package com.gtt.srs.service.impl;

import com.gtt.srs.entity.Missingpersons;
import com.gtt.srs.repository.MissPersonRepository;
import com.gtt.srs.service.MissPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MissPersonServiceImpl implements MissPersonService {
    @Autowired
    private MissPersonRepository missPersonRepository;
    private Missingpersons missingpersons = new Missingpersons();
    private List<Missingpersons> missingpersonsList = new ArrayList<>();

    @Override
    public List<Missingpersons> getMissPerson(Integer page, Integer limit) {

        missingpersonsList = missPersonRepository.getMissPerson(page,limit);
        return missingpersonsList;
    }

    @Override
    public Missingpersons getMissPersonsById(Integer missPersonId) {
        missingpersons = missPersonRepository.getMissPersonsById(missPersonId);
        return missingpersons;
    }

    @Override
    public Integer getMissPersonsCount() {
        Integer count = missPersonRepository.getMissPersonsCount();
        return count;
    }

    @Override
    public List<Missingpersons> getMissPersonApp() {
        missingpersonsList = missPersonRepository.getMissPersonApp();
        return missingpersonsList;
    }
}
