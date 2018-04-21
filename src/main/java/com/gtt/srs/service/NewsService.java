package com.gtt.srs.service;

import com.gtt.srs.entity.News;

import java.util.List;

public interface NewsService {
    List<News> getSuccessCases(Integer type);

    News getSuccessCasesById(Integer caseId);

    List<News> getSiteNotice(Integer type);

    News getSiteNoticeById(Integer noticeId);

    List<News> getAntiFraudiTips(Integer type);

    News getAntiFraudTipsById(Integer tipsId);
}
