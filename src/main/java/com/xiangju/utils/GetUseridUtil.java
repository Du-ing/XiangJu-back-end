package com.xiangju.utils;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.client.RestTemplate;

/**
 * 通过官方接口获取用户微信号的相关信息
 */
public class GetUseridUtil {
    public static Object getUserid(String appid,String secret,String code){
        String url = "https://api.weixin.qq.com/sns/jscode2session" +
                "?appid=" + appid +
                "&secret=" + secret +
                "&js_code=" + code +
                "&grant_type=authorization_code";
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(url, String.class);
        Object json = JSONObject.parse(response);
//        System.out.println(json);
        return json;
    }
}
