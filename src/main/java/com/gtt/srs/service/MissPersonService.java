package com.gtt.srs.service;

import com.gtt.srs.entity.Missingpersons;

import java.util.List;

public interface MissPersonService {
    List<Missingpersons> getMissPerson();

    Missingpersons getMissPersonsById(Integer missPersonId);
}
