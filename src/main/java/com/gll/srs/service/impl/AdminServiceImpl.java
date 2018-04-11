package com.gll.srs.service.impl;

import com.gll.srs.entity.News;
import com.gll.srs.model.Missingpersons;
import com.gll.srs.repository.AdminRepository;
import com.gll.srs.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminRepository adminRepository;
    private List<News> newsList = new ArrayList<>();
    private List<com.gll.srs.model.News> newss = new ArrayList<>();
    private List<Missingpersons> missingpersonsList = new ArrayList<>();
    private List<com.gll.srs.entity.Missingpersons> missingpersons = new ArrayList<>();

    @Override
    public List<Missingpersons> getMissPersonsInfo() {
        missingpersonsList = new ArrayList<>();
        missingpersons = adminRepository.getMissPersonsInfo();
        for (com.gll.srs.entity.Missingpersons missPerson : missingpersons
                ) {
            Missingpersons missingpersons = new Missingpersons();
            missingpersons.setPersonsId(missPerson.getPersonsId());
            missingpersons.setPersonsName(missPerson.getPersonsName());
            missingpersons.setPersonsAge(missPerson.getPersonsAge());
            missingpersons.setPersonsAddress(missPerson.getPersonsAddress());
            missingpersons.setPersonsFeature(missPerson.getPersonsFeature());
            missingpersons.setPersonsGender(missPerson.getPersonsGender());
            missingpersons.setPersonsRescueunit(missPerson.getPersonsRescueunit());
            missingpersons.setPersonsReleasedate(missPerson.getPersonsReleasedate());
            missingpersons.setPersonsNote(missPerson.getPersonsNote());
            missingpersons.setPersonsDiscoverySites(missPerson.getPersonsDiscoverySites());
            missingpersons.setPersonsDna(missPerson.getPersonsDna());
            missingpersons.setPersonsContact(missPerson.getPersonsContact());
            missingpersons.setPersonsBodyheight(missPerson.getPersonsBodyheight());
            missingpersons.setPersonsDateDiscovered(missPerson.getPersonsDateDiscovered());
            missingpersons.setPersonsDress(missPerson.getPersonsDress());
            missingpersons.setOption("<div><i class=\"layui-icon\" style=\"font-size: 30px;color: #009688\" id=\"missInfo_edit\">&#xe642;</i></i><i class=\"layui-icon\"style=\"font-size: 30px;color: #009688\" id=\"missInfo_del\">&#xe640;</i></div>");
            missingpersonsList.add(missingpersons);

        }

        return missingpersonsList;
    }

    @Override
    public Integer deleteMissInfo(Integer missInfoId) {
        Integer result = adminRepository.deleteMissInfo(missInfoId);
        return result;
    }

    @Override
    public com.gll.srs.entity.Missingpersons getMissPersonsInfoById(Integer missInfoId) {
        com.gll.srs.entity.Missingpersons missingpersons = adminRepository.getMissPersonsInfoById(missInfoId);
        return missingpersons;
    }

    @Override
    public int UpdateMissInfoById(Integer missInfoId, com.gll.srs.entity.Missingpersons missingpersons) {
        int value = adminRepository.UpdateMissInfoById(missInfoId, missingpersons);
        return value;
    }

    @Override
    public Integer deleteMessage(Integer messageID) {
        Integer value = adminRepository.deleteMessage(messageID);
        return value;
    }

    @Override
    public List<com.gll.srs.model.News> getNews() {
        newss = new ArrayList<>();
        newsList = adminRepository.getNews();
        for (News news : newsList
                ) {
            com.gll.srs.model.News news1 = new com.gll.srs.model.News();
            news1.setNewsId(news.getNewsId());
            news1.setNewsContent(news.getNewsContent());
            news1.setNewsDate(news.getNewsDate());
            news1.setNewsTitle(news.getNewsTitle());
            if (news.getNewsType() == 0) {
                news1.setType("成功案例");
            } else if (news.getNewsType() == 1) {
                news1.setType("本站公告");
            } else {
                news1.setType("防骗技巧");
            }

            news1.setOption("<div><i class=\"layui-icon\" style=\"font-size: 30px;color: #009688\" id=\"news_edit\">&#xe642;</i></i><i class=\"layui-icon\"style=\"font-size: 30px;color: #009688\" id=\"news_del\">&#xe640;</i></div>");

            newss.add(news1);

        }
        return newss;
    }

    @Override
    public Integer deleteNews(Integer newsId) {
        Integer value = adminRepository.deleteNews(newsId);
        return value;
    }

    @Override
    public News getNewsById(Integer newsId) {
        News news = new News();
        news = adminRepository.getNewsById(newsId);
        return news;
    }

    @Override
    public Integer UpdateNewsById(News news, Integer newsId) {
        Integer value = adminRepository.UpdateNewsById(news, newsId);
        return value;
    }

    @Override
    public Integer addNews(News news) {
        Date time = new Date();
        SimpleDateFormat sf = new SimpleDateFormat();
        String date = sf.format(time);
        Integer value = adminRepository.addNews(news,date);
        return value;
    }
}
