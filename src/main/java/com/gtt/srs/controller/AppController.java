package com.gtt.srs.controller;

import com.gtt.srs.entity.Missingpersons;
import com.gtt.srs.model.DownloadRepsoe;
import com.gtt.srs.model.JsonResult;
import com.gtt.srs.entity.User;
import com.gtt.srs.service.AppService;
import com.gtt.srs.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class AppController {
    private User user = new User();
    private String root = null;
    private JsonResult jsonResult = new JsonResult();
    private List<Missingpersons> missingpersons = new ArrayList<>();
    private DownloadRepsoe dp = new DownloadRepsoe();
    private Integer userID = null;
    @Autowired
    private AppService appService;

    @Autowired
    private IndexService indexService;

    @PostMapping(path = "/appLogin")
    public JsonResult Login(HttpServletRequest request, @RequestParam String userName, @RequestParam String userPsw) {

        jsonResult = new JsonResult();
        user = appService.login(userName, userPsw);
        if (ObjectUtils.isEmpty(user)) {
            jsonResult.setResult("fail");
        } else {
            jsonResult.setResult("success");
        }
        jsonResult.setData(user);


        return jsonResult;
    }

    @PostMapping(path = "/setHistoricalRecords")
    public void setHistoricalRecords(@RequestParam Integer userId, @RequestParam Integer missPersonId) {
        jsonResult = new JsonResult();
        appService.setHistoricalRecords(userId, missPersonId);
    }


    @PostMapping(path = "/getRecordHistoryByUserId")
    public JsonResult getRecordHistoryByUserId(@RequestParam Integer userId) {
        jsonResult = new JsonResult();
        missingpersons = appService.getRecordHistoryByUserId(userId);
        jsonResult.setData(missingpersons);

        return jsonResult;
    }

    //    多头像上传
    @PostMapping(path = "/image/upload")
    public DownloadRepsoe imageMissPersonPic(MultipartFile file) {
        dp = new DownloadRepsoe();

        if (null != file) {
            String myFileName = file.getOriginalFilename();// 文件原名称
            userID = indexService.getPersonsCount();
            indexService.putPersonsPic(myFileName, userID - 1);
            try {
                root = String.valueOf(ResourceUtils.getURL("application.properties"));
                System.out.println(root);

            } catch (Exception e) {
                e.printStackTrace();
            }
            String pathname = root.split("file:/")[1].split("application.properties")[0] + "/static/missImage";
            File fileDir = new File(pathname);
            if (!fileDir.exists()) { //如果不存在 则创建
                fileDir.mkdirs();
            }
            String path = pathname + "/" + myFileName;
            File localFile = new File(path);
            try {
                file.transferTo(localFile);
                dp.setCode(0);
                dp.setMsg("");
                dp.setData(null);
                return dp;
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("文件为空");
        }
        return dp;

    }
}
