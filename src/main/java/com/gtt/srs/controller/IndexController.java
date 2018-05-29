package com.gtt.srs.controller;

import com.alibaba.fastjson.JSON;
import com.gtt.srs.entity.Message;
import com.gtt.srs.entity.Missingpersons;
import com.gtt.srs.entity.Volunteer;
import com.gtt.srs.model.*;
import com.gtt.srs.service.IndexService;
import com.gtt.srs.service.MissPersonService;
import com.gtt.srs.utils.ImageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class IndexController {
    @Autowired
    private IndexService indexService;
    @Autowired
    private MissPersonService missPersonService;
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

    //    失踪人员详细信息页面图片上传
    @PostMapping(path = "/upload/MissPersonPic")
    public DownloadRepsoe uploadMissPersonPic(MultipartFile file, @RequestParam Integer userID) {
        dp = new DownloadRepsoe();
        String filename = ImageHelper.path + "\\static\\missImage\\";
        if (null != file) {
            String myFileName = file.getOriginalFilename();// 文件原名称
            try {
                root = String.valueOf(ResourceUtils.getURL("application.properties"));
                System.out.println(root);

            } catch (Exception e) {
                e.printStackTrace();
            }
//            将文件存储在missImage文件夹中
            String pathname = root.split("file:/")[1].split("application.properties")[0] + "/static/missImage";
            File fileDir = new File(pathname);
            //
            if (!fileDir.exists()) { //如果不存在 则创建
                fileDir.mkdirs();
            }
            String path = pathname + "/" + myFileName;
            File localFile = new File(path);

            //hashCode
            try {
                file.transferTo(localFile);
                String uploadHashCode = produceFingerPrint(filename + myFileName);
                indexService.putPersonsPic(myFileName, userID, uploadHashCode);
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
    public DownloadRepsoe imageMissPersonPic(MultipartFile file, @RequestParam String symbole) {
        dp = new DownloadRepsoe();
        List<String> hashCodes = new ArrayList<String>();
        String filename = ImageHelper.path + "\\static\\missImage\\";
        //  String hashCode = null;
        if (null != file) {
            String myFileName = file.getOriginalFilename();// 文件原名称
            Integer userID = indexService.getPersonsCount();
            //   String sourceHashCode = produceFingerPrint(filename + "b.jpg");
            // indexService.putPersonsPic(myFileName, userID-1);
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
                String hashCode = produceFingerPrint(filename + myFileName);
                if (symbole.equals("releaseInfo")) {
                    indexService.putPersonsPic(myFileName, userID, hashCode);
                }
//                找到所有的Hash Code
                Map<Integer, String> maps = indexService.getAllHashCode();
                missingpersons = new Missingpersons();
                for (Map.Entry<Integer, String> entry : maps.entrySet()) {
                    if (null != entry.getValue()) {
                        int value = hammingDistance(hashCode, entry.getValue());
                        System.out.println("相似值:    " + value);
                        if (value < 5 || value == 5) {
                            missingpersons = new Missingpersons();
                            missingpersons = missPersonService.getMissPersonsById(entry.getKey());
                        }
                        System.out.println("Value is:   " + value);
                    } else {
                        System.out.println("hashCode 为空");

                    }
                }
                System.out.println("Code:   " + hashCode);
                dp.setCode(0);
                dp.setMsg(missingpersons.getPersonsName());
                dp.setData(null);
                return dp;
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

            //         String sourceHashCode = produceFingerPrint(filename + "a-8.jpg");
//            indexService.putPersonsPic(myFileName, userID-1);
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
        if (value != 0) {
            result.setResult("success");
        } else {
            result.setResult("fail");
        }
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


    /**
     * 计算"汉明距离"（Hamming distance）。
     * 如果不相同的数据位不超过5，就说明两张图片很相似；如果大于10，就说明这是两张不同的图片。
     *
     * @param sourceHashCode 源hashCode
     * @param hashCode       与之比较的hashCode
     */
    public static int hammingDistance(String sourceHashCode, String hashCode) {
        int difference = 0;
        int len = sourceHashCode.length();

        for (int i = 0; i < len; i++) {
            if (sourceHashCode.charAt(i) != hashCode.charAt(i)) {
                difference++;
            }
        }

        return difference;
    }

    /**
     * 生成图片指纹
     *
     * @param filename 文件名
     * @return 图片指纹
     */
    public static String produceFingerPrint(String filename) {
        BufferedImage source = ImageHelper.readPNGImage(filename);// 读取文件

        int width = 8;
        int height = 8;

        // 第一步，缩小尺寸。
        // 将图片缩小到8x8的尺寸，总共64个像素。这一步的作用是去除图片的细节，只保留结构、明暗等基本信息，摒弃不同尺寸、比例带来的图片差异。
        BufferedImage thumb = ImageHelper.thumb(source, width, height, false);

        // 第二步，简化色彩。
        // 将缩小后的图片，转为64级灰度。也就是说，所有像素点总共只有64种颜色。
        int[] pixels = new int[width * height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                pixels[i * height + j] = ImageHelper.rgbToGray(thumb.getRGB(i, j));
            }
        }

        // 第三步，计算平均值。
        // 计算所有64个像素的灰度平均值。
        int avgPixel = ImageHelper.average(pixels);

        // 第四步，比较像素的灰度。
        // 将每个像素的灰度，与平均值进行比较。大于或等于平均值，记为1；小于平均值，记为0。
        int[] comps = new int[width * height];
        for (int i = 0; i < comps.length; i++) {
            if (pixels[i] >= avgPixel) {
                comps[i] = 1;
            } else {
                comps[i] = 0;
            }
        }

        // 第五步，计算哈希值。
        // 将上一步的比较结果，组合在一起，就构成了一个64位的整数，这就是这张图片的指纹。组合的次序并不重要，只要保证所有图片都采用同样次序就行了。
        StringBuffer hashCode = new StringBuffer();
        for (int i = 0; i < comps.length; i += 4) {
            int result = comps[i] * (int) Math.pow(2, 3) + comps[i + 1] * (int) Math.pow(2, 2) + comps[i + 2] * (int) Math.pow(2, 1) + comps[i + 2];
            hashCode.append(binaryToHex(result));
        }

        // 得到指纹以后，就可以对比不同的图片，看看64位中有多少位是不一样的。
        return hashCode.toString();
    }

    /**
     * 二进制转为十六进制
     * //  * @param int binary
     *
     * @return char hex
     */
    private static char binaryToHex(int binary) {
        char ch = ' ';
        switch (binary) {
            case 0:
                ch = '0';
                break;
            case 1:
                ch = '1';
                break;
            case 2:
                ch = '2';
                break;
            case 3:
                ch = '3';
                break;
            case 4:
                ch = '4';
                break;
            case 5:
                ch = '5';
                break;
            case 6:
                ch = '6';
                break;
            case 7:
                ch = '7';
                break;
            case 8:
                ch = '8';
                break;
            case 9:
                ch = '9';
                break;
            case 10:
                ch = 'a';
                break;
            case 11:
                ch = 'b';
                break;
            case 12:
                ch = 'c';
                break;
            case 13:
                ch = 'd';
                break;
            case 14:
                ch = 'e';
                break;
            case 15:
                ch = 'f';
                break;
            default:
                ch = ' ';
        }
        return ch;
    }


}