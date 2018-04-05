package com.gll.srs.service.impl;

import com.gll.srs.entity.Antifraudtips;
import com.gll.srs.entity.Sitenotice;
import com.gll.srs.entity.Successcase;
import com.gll.srs.model.JsonResult;
import com.gll.srs.repository.NewsRepository;
import com.gll.srs.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {
    @Autowired
    private NewsRepository newsRepository;
    private Successcase successcase = new Successcase();
    private Sitenotice sitenotice = new Sitenotice();
    private Antifraudtips antifraudtips = new Antifraudtips();
    private List<Successcase> successcaseList = new ArrayList<>();
    private List<Sitenotice> sitenoticeList = new ArrayList<>();
    private List<Antifraudtips> antifraudtipsList = new ArrayList<>();

    @Override
    public List<Successcase> getSuccessCases() {
        successcaseList = newsRepository.getSuccessCases();
        return successcaseList;
    }

    @Override
    public Successcase getSuccessCasesById(Integer caseId) {
        successcase = new Successcase();
        successcase = newsRepository.getSuccessCasesById(caseId);

        return successcase;
    }

    @Override
    public List<Sitenotice> getSiteNotice() {
        sitenoticeList = newsRepository.getSiteNotice();
        return sitenoticeList;
    }

    @Override
    public Sitenotice getSiteNoticeById(Integer noticeId) {
        sitenotice = new Sitenotice();
        sitenotice = newsRepository.getSiteNoticeById(noticeId);

        return sitenotice;
    }

    @Override
    public List<Antifraudtips> getAntiFraudiTips() {
        antifraudtipsList = newsRepository.getAntiFraudiTips();
        return antifraudtipsList;
    }

    @Override
    public Antifraudtips getAntiFraudTipsById(Integer tipsId) {
        antifraudtips = new Antifraudtips();
        antifraudtips = newsRepository.getAntiFraudTipsById(tipsId);

        return antifraudtips;
    }
}
