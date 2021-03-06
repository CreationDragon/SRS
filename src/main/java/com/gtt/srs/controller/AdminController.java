package com.gtt.srs.controller;

import com.alibaba.fastjson.JSON;
import com.gtt.srs.entity.News;
import com.gtt.srs.model.JsonResult;
import com.gtt.srs.model.MissTableInfo;
import com.gtt.srs.model.NewsTableInfo;
import com.gtt.srs.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AdminController {
    @Autowired
    private AdminService adminService;
    private JsonResult result = new JsonResult();
    private News news = new News();
    private List<News> newsList = new ArrayList<>();
    private List<com.gtt.srs.model.News> newss = new ArrayList<>();
    private List<com.gtt.srs.model.Missingpersons> missingpersonsList = new ArrayList<>();

    @GetMapping(path = "/admin/getMissPersonsInfo")
    public String getMissPersonsInfo(@RequestParam Integer page, @RequestParam Integer limit) {
        MissTableInfo missTableInfo = new MissTableInfo();
        missingpersonsList = adminService.getMissPersonsInfo(page,limit);
        missTableInfo.setCode(0);
        missTableInfo.setCount(adminService.getMissPersonsInfoCount());
        missTableInfo.setData(missingpersonsList);
        missTableInfo.setMsg("");

        String missInfo = JSON.toJSONString(missTableInfo);

        return missInfo;

    }

    @PostMapping(path = "/admin/deleteMissInfo")
    public JsonResult deleteMissInfo(@RequestParam Integer MissInfoId) {
        result = new JsonResult();
        Integer res = adminService.deleteMissInfo(MissInfoId);
        if (null != res) {
            result.setResult("success");
        } else {
            result.setResult("faile");
        }
        return result;
    }

    @PostMapping(path = "/admin/getMissInfoById")
    public JsonResult getMissInfoById(@RequestParam Integer MissInfoId) {
        result = new JsonResult();
        com.gtt.srs.entity.Missingpersons missingpersons = adminService.getMissPersonsInfoById(MissInfoId);
        result.setData(missingpersons);

        return result;
    }

    @PostMapping(path = "/admin/UpdateMissInfoById")
    public JsonResult UpdateMissInfoById(@RequestParam Integer MissInfoId, @RequestParam String MissPersonsInfo) {
        result = new JsonResult();
        com.gtt.srs.entity.Missingpersons missingpersons = JSON.parseObject(MissPersonsInfo, com.gtt.srs.entity.Missingpersons.class);
        int value = adminService.UpdateMissInfoById(MissInfoId, missingpersons);

        if (value != 0) {
            result.setResult("success");
        } else {
            result.setResult("fail");
        }
        result.setData(value);
        return result;
    }

    @PostMapping(path = "/admin/deleteMessage")
    public JsonResult deleteMessage(@RequestParam Integer messageID) {
        result = new JsonResult();
        Integer value = adminService.deleteMessage(messageID);
        if (value != 0) {
            result.setResult("success");
        } else {
            result.setResult("fail");
        }
        result.setData(value);

        return result;
    }

    @GetMapping(path = "/admin/getNews")
    public String getNews(@RequestParam Integer page, @RequestParam Integer limit) {

        NewsTableInfo newsTableInfo = new NewsTableInfo();
        newss = new ArrayList<>();
        newss = adminService.getNews(page,limit);
        newsTableInfo.setCode(0);
        newsTableInfo.setCount(adminService.getNewsCount());
        newsTableInfo.setData(newss);
        newsTableInfo.setMsg("");

        String newstableinfo = JSON.toJSONString(newsTableInfo);

        return newstableinfo;
    }

    @PostMapping(path = "/admin/deleteNews")
    public JsonResult deleteNews(@RequestParam Integer NewsId) {
        result = new JsonResult();
        Integer value = adminService.deleteNews(NewsId);
        if (value != 0) {
            result.setResult("success");
        } else {
            result.setResult("fail");
        }
        result.setData(value);

        return result;
    }

    @PostMapping(path = "/admin/getNewsById")
    public JsonResult getNewsById(@RequestParam Integer NewsId) {
        result = new JsonResult();
        News news = new News();
        news = adminService.getNewsById(NewsId);
        result.setData(news);

        return result;
    }

    @PostMapping(path = "/admin/UpdateNewsById")
    public JsonResult UpdateNewsById(@RequestParam Integer NewsId, @RequestParam String News) {
        result = new JsonResult();
        com.gtt.srs.entity.News news = new News();
        news = JSON.parseObject(News, News.class);
        Integer value = adminService.UpdateNewsById(news, NewsId);

        if (value != 0) {
            result.setResult("success");
        } else {
            result.setResult("fail");
        }
        result.setData(value);

        return result;
    }

    @PostMapping(path = "/admin/AddNews")
    public JsonResult AddNews(@RequestParam String News) {
        result = new JsonResult();

        news = JSON.parseObject(News, com.gtt.srs.entity.News.class);


        Integer value = adminService.addNews(news);

        return result;
    }
}