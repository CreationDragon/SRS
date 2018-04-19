package com.gll.srs.service;

import com.gll.srs.entity.News;
import com.gll.srs.model.Missingpersons;

import java.util.List;

public interface AdminService {
    List<Missingpersons> getMissPersonsInfo(Integer page, Integer limit);

    Integer deleteMissInfo(Integer missInfoId);

    com.gll.srs.entity.Missingpersons getMissPersonsInfoById(Integer missInfoId);

    int UpdateMissInfoById(Integer missInfoId, com.gll.srs.entity.Missingpersons missingpersons);

    Integer deleteMessage(Integer messageID);

    List<com.gll.srs.model.News> getNews(Integer page, Integer limit);

    Integer deleteNews(Integer newsId);

    News getNewsById(Integer newsId);

    Integer UpdateNewsById(News news, Integer newsId);

    Integer addNews(News news);

    int getMissPersonsInfoCount();

    int getNewsCount();
}
