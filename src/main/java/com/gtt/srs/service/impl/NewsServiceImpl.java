package com.gtt.srs.service.impl;

import com.gtt.srs.entity.Antifraudtips;
import com.gtt.srs.entity.News;
import com.gtt.srs.entity.Sitenotice;
import com.gtt.srs.repository.NewsRepository;
import com.gtt.srs.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {
    @Autowired
    private NewsRepository newsRepository;
    private News news = new News();
    private List<News> newsList = new ArrayList<>();
    private List<Sitenotice> sitenoticeList = new ArrayList<>();
    private List<Antifraudtips> antifraudtipsList = new ArrayList<>();

    @Override
    public List<News> getSuccessCases(Integer type) {
        newsList = newsRepository.getSuccessCases(type);
        return newsList;
    }

    @Override
    public News getSuccessCasesById(Integer caseId) {
        news = new News();
        news = newsRepository.getSuccessCasesById(caseId);

        return news;
    }

    @Override
    public List<News> getSiteNotice(Integer type) {
        newsList = newsRepository.getSiteNotice(type);
        return newsList;
    }

    @Override
    public News getSiteNoticeById(Integer noticeId) {
        news = new News();
        news = newsRepository.getSiteNoticeById(noticeId);

        return news;
    }

    @Override
    public List<News> getAntiFraudiTips(Integer type) {
        newsList = newsRepository.getAntiFraudiTips(type);
        return newsList;
    }

    @Override
    public News getAntiFraudTipsById(Integer tipsId) {
        news = new News();
        news = newsRepository.getAntiFraudTipsById(tipsId);

        return news;
    }
}
