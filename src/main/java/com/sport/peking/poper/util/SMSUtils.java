package com.sport.peking.poper.util;


import com.alibaba.fastjson.JSONObject;
import com.sport.peking.poper.encode.WebNetEncode;
import com.sport.peking.poper.util.CodeProducer;
import com.sport.peking.poper.util.HttpClientUtil;
import com.sport.peking.poper.util.HttpClientUtils;
import org.apache.http.HttpClientConnection;
import org.apache.http.client.HttpClient;
import org.apache.tomcat.util.security.MD5Encoder;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;

/**
 * @author Miracle~
 * create at 2018-08-27
 */
public class SMSUtils {


    public static void main(String[] args) {
        try {
            cloudMSSend("15336655531");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }


    public static String cloudMSSend(String phoneNumber) throws UnsupportedEncodingException {

        String code = CodeProducer.loginCode();
        String uid = "a652365016";
        String pwd = "725410ea772bff57dc8513f9b2da389c";
        String content = "【雪乐山】您本次登录的验证码为：" + code +",有效期100S。如非本人操作，请忽略此短信。";
        String encodeContent = URLEncoder.encode(content, "UTF-8");
        String url = "http://api.sms.cn/sms/?ac=send&uid=" + uid +"&pwd=" + pwd +"&mobile=" + phoneNumber + "&content=" + encodeContent;

        JSONObject jsonObject = HttpClientUtils.httpGet(url);
        System.out.println(jsonObject.toJSONString());
        return code;
    }
}
