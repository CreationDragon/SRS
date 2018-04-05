package com.gll.srs.service;

import com.gll.srs.entity.Antifraudtips;
import com.gll.srs.entity.Sitenotice;
import com.gll.srs.entity.Successcase;

import java.util.List;

public interface NewsService {
    List<Successcase> getSuccessCases();

    Successcase getSuccessCasesById(Integer caseId);

    List<Sitenotice> getSiteNotice();

    Sitenotice getSiteNoticeById(Integer noticeId);

    List<Antifraudtips> getAntiFraudiTips();

    Antifraudtips getAntiFraudTipsById(Integer tipsId);
}
