package com.gtt.srs.service.impl;

import com.gtt.srs.entity.News;
import com.gtt.srs.repository.AdminRepository;
import com.gtt.srs.service.AdminService;
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
    private List<com.gtt.srs.model.News> newss = new ArrayList<>();
    private List<com.gtt.srs.model.Missingpersons> missingpersonsList = new ArrayList<>();
    private List<com.gtt.srs.entity.Missingpersons> missingpersons = new ArrayList<>();

    @Override
    public List<com.gtt.srs.model.Missingpersons> getMissPersonsInfo(Integer page, Integer limit) {
        missingpersonsList = new ArrayList<>();
        missingpersons = adminRepository.getMissPersonsInfo(page,limit);
        for (com.gtt.srs.entity.Missingpersons missPerson : missingpersons
                ) {
            com.gtt.srs.model.Missingpersons missingpersons = new com.gtt.srs.model.Missingpersons();
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
    public com.gtt.srs.entity.Missingpersons getMissPersonsInfoById(Integer missInfoId) {
        com.gtt.srs.entity.Missingpersons missingpersons = adminRepository.getMissPersonsInfoById(missInfoId);
        return missingpersons;
    }

    @Override
    public int UpdateMissInfoById(Integer missInfoId, com.gtt.srs.entity.Missingpersons missingpersons) {
        int value = adminRepository.UpdateMissInfoById(missInfoId, missingpersons);
        return value;
    }

    @Override
    public Integer deleteMessage(Integer messageID) {
        Integer value = adminRepository.deleteMessage(messageID);
        return value;
    }

    @Override
    public List<com.gtt.srs.model.News> getNews(Integer page, Integer limit) {
        newss = new ArrayList<>();
        newsList = adminRepository.getNews(page,limit);
        for (News news : newsList
                ) {
            com.gtt.srs.model.News news1 = new com.gtt.srs.model.News();
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

    @Override
    public int getMissPersonsInfoCount() {
        int count = adminRepository.getMissPersonsInfoCount();
        return count;
    }

    @Override
    public int getNewsCount() {
        int count = adminRepository.getNewsCount();
        return count;
    }
}
