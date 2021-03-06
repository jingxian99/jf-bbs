package com.jfsoft.bbs.controller;


import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.jfsoft.bbs.common.utils.JWTUtils;
import com.jfsoft.bbs.entity.BbsUserEntity;
import com.jfsoft.bbs.service.BbsUserService;
import com.jfsoft.bbs.service.DingDingInterfaceService;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Value("${app.DD_APPID}")
    String DD_APPID;

    @Value("${app.DD_APPSECRET}")
    String DD_APPSECRET;

    @Value("${app.DD_CORPID}")
    String DD_CORPID;

    @Value("${app.DD_CORPSECRET}")
    String DD_CORPSECRET;

    @Value("${app.DD_URL}")
    String DD_URL;

    @Value("${app.webUrl}")
    String webUrl;

    @Autowired
    private BbsUserService bbsUserService;

    @Autowired
    private DingDingInterfaceService dingDingInterfaceService;


    @GetMapping("/login/free")
    public String freeLoginHandel(String code, Model model) {
        logger.info("code" + code);
        String appSecret = "7LkeSfEusPwCez-yfdHiJGFMJbODWXZItH0nTSkU2sACV8wrtfZKwWRVAHpNg4-w";
        String appId = "dingoalp0riozjvog7wzkp";
        // 根据timestamp, appSecret计算签名值
        com.alibaba.fastjson.JSONObject object = new com.alibaba.fastjson.JSONObject();
        try {
            String timestamp = String.valueOf(System.currentTimeMillis());
            String stringToSign = timestamp;
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(new SecretKeySpec(appSecret.getBytes("UTF-8"), "HmacSHA256"));
            byte[] signatureBytes = mac.doFinal(stringToSign.getBytes("UTF-8"));
            String signature = new String(Base64.encodeBase64(signatureBytes));
            String urlEncodeSignature = urlEncode(signature, "UTF-8");
            String url = "https://oapi.dingtalk.com/sns/getuserinfo_bycode?signature=" + urlEncodeSignature + "&timestamp=" + timestamp +
                    "&accessKey=" + appId;
            Map<String, Object> params = new HashMap<>();
            params.put("tmp_auth_code", code);
            object = JSON.parseObject(HttpUtil.post(url, JSON.toJSONString(params)));
            if (object.getInteger("errcode") == 0) {
                com.alibaba.fastjson.JSONObject userInfo = (com.alibaba.fastjson.JSONObject) object.get("user_info");
                String unionId = null;
                String token = null;
                unionId = (String) userInfo.get("unionid");

                EntityWrapper<BbsUserEntity> wrapper = new EntityWrapper<>();
                wrapper.eq("union_id", unionId);
                // 查询unionId下所有的子账号
                List<BbsUserEntity> userList = bbsUserService.selectList(wrapper);
                if (userList.size() == 0) {
                    //insert
                    BbsUserEntity bbsUserEntity = new BbsUserEntity();
                    bbsUserEntity.setInitTime(new Date());
                    bbsUserEntity.setUnionId(unionId);
                    bbsUserService.insert(bbsUserEntity);
                    int id = bbsUserEntity.getId();
                    token = JWTUtils.sign(String.valueOf(id), unionId);
                    return "redirect:" + webUrl + "?token=" + token;
                } else {
                    token = JWTUtils.sign(String.valueOf(userList.get(0).getId()), unionId);
                    return "redirect:" + webUrl + "?token=" + token;
                }
            } else {
                logger.info("获取用户信息失败");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("error", object.toJSONString());
        return "error";
    }

    // encoding参数使用utf-8
    public static String urlEncode(String value, String encoding) {
        if (value == null) {
            return "";
        }
        try {
            String encoded = URLEncoder.encode(value, encoding);
            return encoded.replace("+", "%20").replace("*", "%2A")
                    .replace("~", "%7E").replace("/", "%2F");
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException("FailedToEncodeUri", e);
        }
    }


    @GetMapping("/login/ding")
    public String getUserFromDingDing(String code) {
        logger.info("code: " + code);

        String token = null;

        String accessToken = dingDingInterfaceService.getAccessToken().getStr("access_token");
        logger.info("access_token: " + accessToken);
        String companyToken = dingDingInterfaceService.getCompanyToken().getStr("access_token");
        logger.info("companyToken: " + companyToken);
        String unionId = dingDingInterfaceService.getUnionId(accessToken, code).getStr("unionid");
        logger.info("unionId: " + unionId);
        String userId = dingDingInterfaceService.getUserId(companyToken, unionId).getStr("userid");
        logger.info("unionId: " + userId);
        JSONObject user = dingDingInterfaceService.getUser(companyToken, userId);

        EntityWrapper<BbsUserEntity> wrapper = new EntityWrapper<>();
        wrapper.eq("union_id", unionId);
        // 查询unionId下所有的子账号
        List<BbsUserEntity> userList = bbsUserService.selectList(wrapper);
        if (userList.size() == 0) {
            //insert
            BbsUserEntity bbsUserEntity = new BbsUserEntity();
            bbsUserEntity.setInitTime(new Date());
            bbsUserEntity.setUnionId(unionId);
            bbsUserService.insert(bbsUserEntity);
            int id = bbsUserEntity.getId();
            token = JWTUtils.sign(String.valueOf(id), unionId);
            return "redirect:" + webUrl + "?token=" + token;
        } else {
            token = JWTUtils.sign(String.valueOf(userList.get(0).getId()), unionId);
            return "redirect:" + webUrl + "?token=" + token;
        }

    }

    /**
     * 切换账号
     *
     * @param userId
     * @return
     */
    @RequestMapping("/turnAccount")
    public String getToken(Model model, Integer userId) {
        EntityWrapper<BbsUserEntity> wrapper = new EntityWrapper<>();
        wrapper.eq("id", userId);
        BbsUserEntity bbsUserEntity = bbsUserService.selectOne(wrapper);
        if (bbsUserEntity != null) {
            String token = JWTUtils.sign(String.valueOf(userId), bbsUserEntity.getUnionId());
            return "redirect:" + webUrl + "?token=" + token;
        } else {
            model.addAttribute("error", "未查询到账号信息");
            return "error";
        }
    }

    public static void main(String[] args) {
        System.out.println(JWTUtils.sign(String.valueOf(79), "iigDSnDWgTtiSiiF5C8d6JLdgiEiE"));
//        System.out.println(JWTUtils.sign(String.valueOf(77), "gDJv4xQT2iivMfSaTobeNFAiaaa"));
    }
}
