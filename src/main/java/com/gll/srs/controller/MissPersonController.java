package com.gll.srs.controller;

import com.gll.srs.entity.Missingpersons;
import com.gll.srs.model.JsonResult;
import com.gll.srs.service.MissPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MissPersonController {
    @Autowired
    private MissPersonService missPersonService;
    private JsonResult result = new JsonResult();
    private Missingpersons missingpersons = new Missingpersons();
    private List<Missingpersons> missingpersonsList = new ArrayList<>();

    @PostMapping(path = "/getMissPerson")
    public JsonResult getMissPerson() {
        result = new JsonResult();
        missingpersonsList = missPersonService.getMissPerson();
        result.setResult("success");
        result.setData(missingpersonsList);

        return result;
    }

    @PostMapping(path = "/getMissPersonsById")
    public JsonResult getMissPersonsById(@RequestParam Integer MissPersonId) {
        result = new JsonResult();
        missingpersons = missPersonService.getMissPersonsById(MissPersonId);
        result.setResult("success");
        result.setData(missingpersons);

        return result;
    }

}
