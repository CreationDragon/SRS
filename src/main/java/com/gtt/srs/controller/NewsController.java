package com.gtt.srs.controller;

import com.gtt.srs.entity.Antifraudtips;
import com.gtt.srs.entity.News;
import com.gtt.srs.entity.Sitenotice;
import com.gtt.srs.model.JsonResult;
import com.gtt.srs.service.NewsService;
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
    private News news = new News();
    private List<News> newsList = new ArrayList<>();
    private List<Sitenotice> sitenoticeList = new ArrayList<>();
    private List<Antifraudtips> antifraudtipsList = new ArrayList<>();

    @PostMapping(path = "/getSuccessCases")
    public JsonResult getSuccessCases(@RequestParam Integer type) {
        System.out.println("type:   " + type);
        result = new JsonResult();
        newsList = newsService.getSuccessCases(type);
        if (newsList.size() != 0) {
            result.setResult("success");
        } else {
            result.setResult("fail");
        }
        result.setData(newsList);

        return result;
    }

    @PostMapping(path = "/getSuccessCasesById")
    public JsonResult getSuccessCasesById(@RequestParam Integer caseId) {
        result = new JsonResult();
        news = new News();
        news = newsService.getSuccessCasesById(caseId);

        result.setResult("success");
        result.setData(news);

        return result;
    }

    @PostMapping(path = "/getSiteNotice")
    public JsonResult getSiteNotice(@RequestParam Integer type) {
        result = new JsonResult();
        newsList = newsService.getSiteNotice(type);
        if (newsList.size() != 0) {
            result.setResult("success");
        } else {
            result.setResult("fail");
        }
        result.setData(newsList);
        return result;
    }

    @PostMapping(path = "/getSiteNoticeById")
    public JsonResult getSiteNoticeById(@RequestParam Integer noticeId) {
        result = new JsonResult();
        news = newsService.getSiteNoticeById(noticeId);
        result.setResult("success");
        result.setData(news);
        return result;
    }

    @PostMapping(path = "/getAntiFraudiTips")
    public JsonResult getAntiFraudiTips(@RequestParam Integer type) {
        result = new JsonResult();
        newsList = newsService.getAntiFraudiTips(type);
        if (antifraudtipsList.size() != 0) {
            result.setResult("success");
        } else {
            result.setResult("fail");
        }
        result.setData(newsList);
        return result;
    }

    @PostMapping(path = "/getAntiFraudTipsById")
    public JsonResult getAntiFraudTipsById(@RequestParam Integer tipsId) {
        result = new JsonResult();
        news = newsService.getAntiFraudTipsById(tipsId);

        result.setResult("success");
        result.setData(news);

        return result;
    }


}
