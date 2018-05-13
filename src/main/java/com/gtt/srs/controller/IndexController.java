package com.gtt.srs.controller;

import com.alibaba.fastjson.JSON;
import com.gtt.srs.entity.Message;
import com.gtt.srs.entity.Missingpersons;
import com.gtt.srs.entity.Volunteer;
import com.gtt.srs.model.*;
import com.gtt.srs.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class IndexController {
    @Autowired
    private IndexService indexService;
    private User user = new User();
    private String msg;
    private HttpSession session;
    private String root = null;
    private Integer userId = null;
    private JsonResult result = new JsonResult();
    private DownloadRepsoe dp = new DownloadRepsoe();
    private Volunteer volunteer = new Volunteer();
    private Missingpersons missingpersons = new Missingpersons();
    private List<Missingpersons> missingpersonsList = new ArrayList<>();
    private List<User> userList = new ArrayList<>();
    private List<Volunteer> volunteerList = new ArrayList<>();

    @PostMapping(path = "/index")
    public JsonResult index(HttpServletRequest request, Model model) {
        result = new JsonResult();

        String userName = (String) request.getSession().getAttribute("userName");
        String userPsw = (String) request.getSession().getAttribute("userPsw");
        Integer userId = (Integer) request.getSession().getAttribute("user_id");

        user.setUserName(userName);
        user.setUserPsw(userPsw);
        if (null != userId) {
            user.setUserId(userId);
        }


        result.setResult("success");
        result.setData(user);

        model.addAttribute("data", user);

        return result;

    }

    @RequestMapping(path = "/admin/index")
    public JsonResult getAdminIndex(HttpServletRequest request, Model model) {

        String userName = (String) request.getSession().getAttribute("user_name");
        String userPsw = (String) request.getSession().getAttribute("user_psw");

        user.setUserName(userName);
        user.setUserPsw(userPsw);


        result.setResult("success");
        result.setData(user);

        model.addAttribute("data", user);

        return result;
    }

    @PostMapping(path = "/getAreaById")
    public JsonResult getUserInfoById(@RequestParam String provinceID, @RequestParam String cityID, @RequestParam String districtID) {
        ThreeArea threeArea = new ThreeArea();
        threeArea = indexService.getAreaById(provinceID, cityID, districtID);

        result.setResult("success");
        result.setData(threeArea);
        return result;

    }

    @GetMapping(path = "/area")
    public JsonResult getArea(@RequestParam String id, @RequestParam String sign) {
        Area area = indexService.getArea(id, sign);
        JsonResult result = new JsonResult();
        result.setResult("success");
        result.setData(area);
        return result;
    }

    @PostMapping(path = "/register")
    public JsonResult registerNumber(@RequestParam String register_data, HttpServletRequest request, HttpServletResponse response) {
        User user = JSON.parseObject(register_data, User.class);
        System.out.println(user);


        msg = indexService.registerNumber(user);


        userId = indexService.getRegistersId();

        JsonResult result = new JsonResult();
        if (msg.equals("注册成功")) {
            result.setResult("success");
            //获取Session
            session = request.getSession();
            //将数据存储在Session中
            session.setAttribute("userName", user.getUserName());
            session.setAttribute("userPsw", user.getUserPsw());
            session.setAttribute("user_id", userId);
        } else {
            result.setResult("fail");
        }
        result.setData(msg);
        return result;
    }

    @PostMapping(path = "/getInfo")
    public JsonResult getUserInfo() {
        user = indexService.getUserInfo();

        result.setResult("success");
        result.setData(user);
        return result;
    }

    @PostMapping(path = "/login")
    public JsonResult Login(HttpServletRequest request, @RequestParam String userName, @RequestParam String userPsw) {

        session = request.getSession();
        user = new User();
        user = indexService.login(userName, userPsw);

        if (user.getUserAuthority() == 1) {
            //将数据存储在Session中
            session.setAttribute("user_name", user.getUserName());
            session.setAttribute("user_psw", user.getUserPsw());
            session.setAttribute("user_authority", user.getUserAuthority());
            session.setAttribute("user_id", user.getUserId());

        } else {
            //将数据存储在Session中
            session.setAttribute("userName", user.getUserName());
            session.setAttribute("userPsw", user.getUserPsw());
            session.setAttribute("user_authority", user.getUserAuthority());
            session.setAttribute("user_id", user.getUserId());
        }

        if (user.getUserName() != null) {
            result.setResult("success");
            result.setData(session.getAttribute("user_authority"));
        } else {
            result.setResult("fail");
            result.setData("您还未主册");
        }
        return result;
    }

    @PostMapping(path = "/modifyUserInfo")
    public JsonResult modifyUserInfo(@RequestParam String userID) {
        result = new JsonResult();

        return result;
    }


    @PostMapping(path = "/loginout")
    public JsonResult loginout(HttpServletRequest request, HttpServletResponse response) {
        session = request.getSession(false);
        if (session == null) {
            result.setResult("faile");
            result.setData("faile");
        } else {
            session.removeAttribute("userName");
            session.removeAttribute("userPsw");
            session.removeAttribute("user_id");

            result.setResult("success");
            result.setData("success");
        }

        return result;
    }


    @PostMapping(path = "/admin/loginout")
    public JsonResult adminLoginout(HttpServletRequest request, HttpServletResponse response) {
        session = request.getSession(false);
        if (session == null) {
            result.setResult("faile");
            result.setData("faile");
        } else {
            session.removeAttribute("user_name");
            session.removeAttribute("user_psw");
            session.removeAttribute("user_id");

            result.setResult("success");
            result.setData("success");
        }

        return result;
    }

    @PostMapping(path = "/getAdminInfo")
    public JsonResult getAdminInfo() {
        result = new JsonResult();
        userList = indexService.getAdminInfo();
        result.setResult("success");
        result.setData(userList);

        return result;
    }

    @PostMapping(path = "/putMessage")
    public JsonResult putMessage(@RequestParam String data, @RequestParam Integer userID) {
        Message message = JSON.parseObject(data, Message.class);
        result = new JsonResult();

        int count = indexService.putMessage(message, userID);

        result.setResult("success");
        if (count > 0) {
            result.setResult("success");
            result.setData("留言成功");
        } else {
            result.setResult("faile");
            result.setData("留言失败");
        }
        return result;

    }


    @PostMapping(path = "/upload/head")
    public DownloadRepsoe upload(MultipartFile file, @RequestParam String userID) {
        String myFileName = null;
        dp = new DownloadRepsoe();

        if (null != file) {
            myFileName = file.getOriginalFilename();// 文件原名称
            try {
                root = String.valueOf(ResourceUtils.getURL("application.properties"));
                System.out.println(root);

            } catch (Exception e) {
                e.printStackTrace();
            }
            String pathname = root.split("file:/")[1].split("application.properties")[0] + "/static/headpic";
            File fileDir = new File(pathname);
            if (!fileDir.exists()) { //如果不存在 则创建
                fileDir.mkdirs();
            }
            String path = pathname + "/" + userID + ".jpg";
            File localFile = new File(path);
            try {
                file.transferTo(localFile);
                dp.setCode(0);
                dp.setMsg("");
                dp.setData(null);
                indexService.updatePic(myFileName, Integer.valueOf(userID));
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


    //    单独的一个功能获取用户的ID
    @PostMapping("/getUserId")
    public Integer getUserId() {
        Integer id = null;
        try {
            Integer user_id = (Integer) session.getAttribute("user_id");
            id = user_id;
        } catch (Exception e) {
            id = null;
        }
        return id;
    }

    //    关键字搜索
    @PostMapping(path = "/infoSearch")
    public JsonResult infoSearch(@RequestParam String keyWord) {
        result = new JsonResult();
        missingpersonsList = new ArrayList<>();
        missingpersonsList = indexService.infoSearch(keyWord);
        if (missingpersonsList.size() != 0) {
            result.setResult("success");
        } else {
            result.setResult("fail");
        }
        result.setData(missingpersonsList);

        return result;

    }

    //    失踪信息多头像上传
    @PostMapping(path = "/upload/MissPersonPic")
    public DownloadRepsoe uploadMissPersonPic(MultipartFile file, @RequestParam Integer userID) {
        dp = new DownloadRepsoe();

        if (null != file) {
            String myFileName = file.getOriginalFilename();// 文件原名称
            indexService.putPersonsPic(myFileName, userID);
            try {
                root = String.valueOf(ResourceUtils.getURL("application.properties"));
                System.out.println(root);

            } catch (Exception e) {
                e.printStackTrace();
            }
//            将文件存储在missImage文件夹中
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

    //    多头像上传
    @PostMapping(path = "/image/MissPersonPic")
    public DownloadRepsoe imageMissPersonPic(MultipartFile file) {
        dp = new DownloadRepsoe();

        if (null != file) {
            String myFileName = file.getOriginalFilename();// 文件原名称
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

    //    信息发布
    @PostMapping(path = "/releaseMissInfo")
    public JsonResult releaseMissInfo(@RequestParam String missPersonsInfo, @RequestParam Integer userID) {
        missingpersons = new Missingpersons();
        missingpersons = JSON.parseObject(missPersonsInfo, Missingpersons.class);
        result = new JsonResult();
        int count = indexService.releaseMissInfo(missingpersons, userID);
        if (count != 0) {
            result.setResult("success");
        } else {
            result.setResult("fail");
        }
        result.setData(count);

        return result;
    }

    //     志愿者注册
    @PostMapping(path = "/volunteerRegister")
    public JsonResult volunteerRegister(@RequestParam String volunteerInfo) {
        result = new JsonResult();
        Volunteer volunteer = JSON.parseObject(volunteerInfo, Volunteer.class);
        int value = indexService.volunteerRegister(volunteer);
        return result;
    }

    //    获取志愿者信息
    @PostMapping(path = "/getVolunteer")
    public JsonResult getVolunteer() {
        result = new JsonResult();
        volunteerList = indexService.getVolunteer();
        if (volunteerList.size() != 0) {
            result.setResult("success");
        } else {
            result.setResult("fail");
        }
        result.setData(volunteerList);

        return result;
    }

    //    获取志愿者的详细信息
    @PostMapping(path = "/getVolunteerInfo")
    public JsonResult getVolunteerInfo(@RequestParam Integer volunteerId) {
        result = new JsonResult();
        volunteer = indexService.getVolunteerInfo(volunteerId);

        result.setResult("Success");
        result.setData(volunteer);

        return result;
    }

    //    获取头像
    @PostMapping(path = "/getPersonPic")
    public JsonResult getPersonPic(@RequestParam String MissPersonId) {
        result = new JsonResult();
        Integer missPersonId = Integer.parseInt(MissPersonId);
        List<String> pics = indexService.getPersonPics(missPersonId);
        if (pics.size() != 0) {
            result.setResult("success");
        } else {
            result.setResult("fail");
        }
        result.setData(pics);

        return result;
    }

}