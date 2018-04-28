package com.gtt.srs.service;

import com.gtt.srs.entity.Missingpersons;

import java.util.List;

public interface MissPersonService {
    List<Missingpersons> getMissPerson(Integer page, Integer limit);

    Missingpersons getMissPersonsById(Integer missPersonId);

    Integer getMissPersonsCount();

    List<Missingpersons> getMissPersonApp();
}
