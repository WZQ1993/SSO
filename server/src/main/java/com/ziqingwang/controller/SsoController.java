package com.ziqingwang.controller;

import com.ziqingwang.service.AuthServices;
import com.ziqingwang.constant.ResponseConstant;
import com.ziqingwang.utils.Token;
import com.ziqingwang.utils.TokenUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * Created by ziqingwang on 2017/3/6.
 */
@RestController
@RequestMapping("/sso")
public class SsoController {
    Logger logger= LoggerFactory.getLogger(getClass());
    @Autowired
    private AuthServices authServices;

    @RequestMapping(value = "auth",method = RequestMethod.POST)
    @ResponseBody
    public String login(HttpServletRequest request, HttpServletResponse response)throws Exception{
        String userName= ServletRequestUtils.getStringParameter(request,"userName");
        String passWord= ServletRequestUtils.getStringParameter(request,"passWord");
        if(StringUtils.isEmpty(userName)||StringUtils.isEmpty(passWord)){
            return "error";
        }
        Token token=authServices.login(userName,passWord);
        if(Objects.nonNull(token)){
            response.setHeader("Authorization", TokenUtils.formatToken(token));
        }
        return ResponseConstant.SUCCESS;
    }
    @RequestMapping(value = "refesh",method = RequestMethod.POST)
    @ResponseBody
    public String refesh(HttpServletRequest request, HttpServletResponse response)throws Exception{
        return ResponseConstant.SUCCESS;
    }
    @RequestMapping(value = "verify",method = RequestMethod.POST)
    @ResponseBody
    public String verify(HttpServletRequest request, HttpServletResponse response)throws Exception{
        return ResponseConstant.SUCCESS;
    }
}
