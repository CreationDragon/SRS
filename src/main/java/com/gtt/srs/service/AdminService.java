package com.gtt.srs.service;

import java.util.List;

public interface AdminService {
    List<com.gtt.srs.model.Missingpersons> getMissPersonsInfo(Integer page, Integer limit);

    Integer deleteMissInfo(Integer missInfoId);

    com.gtt.srs.entity.Missingpersons getMissPersonsInfoById(Integer missInfoId);

    int UpdateMissInfoById(Integer missInfoId, com.gtt.srs.entity.Missingpersons missingpersons);

    Integer deleteMessage(Integer messageID);

    List<com.gtt.srs.model.News> getNews(Integer page, Integer limit);

    Integer deleteNews(Integer newsId);

    com.gtt.srs.entity.News getNewsById(Integer newsId);

    Integer UpdateNewsById(com.gtt.srs.entity.News news, Integer newsId);

    Integer addNews(com.gtt.srs.entity.News news);

    int getMissPersonsInfoCount();

    int getNewsCount();
}
