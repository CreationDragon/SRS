package com.gll.srs.service;

import com.gll.srs.entity.Antifraudtips;
import com.gll.srs.entity.News;
import com.gll.srs.entity.Sitenotice;
import com.gll.srs.entity.Successcase;

import java.util.List;

public interface NewsService {
    List<News> getSuccessCases(Integer type);

    News getSuccessCasesById(Integer caseId);

    List<News> getSiteNotice(Integer type);

    News getSiteNoticeById(Integer noticeId);

    List<News> getAntiFraudiTips(Integer type);

    News getAntiFraudTipsById(Integer tipsId);
}
