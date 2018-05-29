package com.gtt.srs.controller;

import com.gtt.srs.entity.Missingpersons;
import com.gtt.srs.model.DownloadRepsoe;
import com.gtt.srs.model.JsonResult;
import com.gtt.srs.entity.User;
import com.gtt.srs.service.AppService;
import com.gtt.srs.service.IndexService;
import com.gtt.srs.service.MissPersonService;
import com.gtt.srs.utils.ImageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class AppController {
    private User user = new User();
    private String root = null;
    private Missingpersons missingperson = new Missingpersons();
    private JsonResult jsonResult = new JsonResult();
    private List<Missingpersons> missingpersons = new ArrayList<>();
    private DownloadRepsoe dp = new DownloadRepsoe();
    private Integer userID = null;
    @Autowired
    private AppService appService;

    @Autowired
    private IndexService indexService;

    @Autowired
    private MissPersonService missPersonService;

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
                for (Map.Entry<Integer, String> entry : maps.entrySet()) {
                    if (null != entry.getValue()) {
                        int value = hammingDistance(hashCode, entry.getValue());
                        if (value < 5 || value == 5) {
                            missingperson = new Missingpersons();
                            missingperson = missPersonService.getMissPersonsById(entry.getKey());
                        }
                        System.out.println("Value is:   " + value);
                    } else {
                        System.out.println("hashCode 为空");
                    }
                }
                System.out.println("Code:   " + hashCode);
                dp.setCode(0);
                dp.setMsg(null);
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
