package com.gll.srs.controller;

import com.gll.srs.entity.Antifraudtips;
import com.gll.srs.entity.Sitenotice;
import com.gll.srs.entity.Successcase;
import com.gll.srs.model.JsonResult;
import com.gll.srs.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class NewsController {
    @Autowired
    private NewsService newsService;
    private JsonResult result;
    private Successcase successcase = new Successcase();
    private Sitenotice sitenotice = new Sitenotice();
    private Antifraudtips antifraudtips = new Antifraudtips();
    private List<Successcase> successcaseList = new ArrayList<>();
    private List<Sitenotice> sitenoticeList = new ArrayList<>();
    private List<Antifraudtips> antifraudtipsList = new ArrayList<>();

    @PostMapping(path = "/getSuccessCases")
    public JsonResult getSuccessCases() {
        result = new JsonResult();
        successcaseList = newsService.getSuccessCases();
        if (successcaseList.size() != 0) {
            result.setResult("success");
            result.setData(successcaseList);
        } else {
            result.setResult("fail");
        }

        return result;
    }

    @PostMapping(path = "/getSuccessCasesById")
    public JsonResult getSuccessCasesById(@RequestParam Integer caseId) {
        result = new JsonResult();
        successcase = new Successcase();
        successcase = newsService.getSuccessCasesById(caseId);

        result.setResult("success");
        result.setData(successcase);

        return result;
    }

    @PostMapping(path = "/getSiteNotice")
    public JsonResult getSiteNotice() {
        result = new JsonResult();
        sitenoticeList = newsService.getSiteNotice();
        if (sitenoticeList.size() != 0) {
            result.setResult("success");
            result.setData(sitenoticeList);
        } else {
            result.setResult("fail");
        }
        return result;
    }

    @PostMapping(path = "/getSiteNoticeById")
    public JsonResult getSiteNoticeById(@RequestParam Integer noticeId) {
        result = new JsonResult();
        sitenotice = newsService.getSiteNoticeById(noticeId);
        result.setResult("success");
        result.setData(sitenotice);
        return result;
    }

    @PostMapping(path = "/getAntiFraudiTips")
    public JsonResult getAntiFraudiTips() {
        result = new JsonResult();
        antifraudtipsList = newsService.getAntiFraudiTips();
        if (antifraudtipsList.size() != 0) {
            result.setResult("success");
            result.setData(antifraudtipsList);
        } else {
            result.setResult("fail");
        }
        return result;
    }

    @PostMapping(path = "/getAntiFraudTipsById")
    public JsonResult getAntiFraudTipsById(@RequestParam Integer tipsId) {
        result = new JsonResult();
        antifraudtips = newsService.getAntiFraudTipsById(tipsId);

        result.setResult("success");
        result.setData(antifraudtips);

        return result;
    }


}
