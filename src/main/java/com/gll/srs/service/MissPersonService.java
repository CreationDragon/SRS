package com.gll.srs.service;

import com.gll.srs.entity.Missingpersons;

import java.util.List;

public interface MissPersonService {
    List<Missingpersons> getMissPerson();

    Missingpersons getMissPersonsById(Integer missPersonId);
}
