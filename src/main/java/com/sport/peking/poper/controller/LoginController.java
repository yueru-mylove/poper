package com.sport.peking.poper.controller;


import com.sport.peking.poper.util.Result;
import com.sport.peking.poper.util.SMSUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;

/**
 * @author miracle
 * create at 2018-08-27 17:04
 */
@RequestMapping("/api")
@RestController
public class LoginController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping("/code/send/{phoneNumber}")
    public Result sendCode(@PathVariable("phoneNumber") String phoneNumber) {

        try {
            SMSUtils.cloudMSSend(phoneNumber);
            return Result.success();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            LOGGER.error("发生异常：{}，请重试！", e.getMessage());
            return Result.error();
        }
    }
}
