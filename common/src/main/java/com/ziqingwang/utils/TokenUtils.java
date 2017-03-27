package com.ziqingwang.utils;

import com.alibaba.fastjson.JSON;

import com.google.common.collect.Maps;
import com.ziqingwang.enumeration.Project;
import com.ziqingwang.to.UserTO;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.util.Base64Utils;
import org.springframework.util.StringUtils;

import java.time.Clock;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * Created by 王梓青 on 2017/3/6.
 */
public class TokenUtils {
    private static final String SECRET ="QWERTYUIOPASDFGHJKLZXCVBNM";
    private static final String TOKEN_SPLIT="\\.";
    public static Optional<Token> creatToken(UserTO user, long expireTime, long effectiveTime){
        if(Objects.isNull(user)){
            return Optional.empty();
        }
        Token token=new Token();
        //TODO 当前毫秒数，后续消除时区影响
        long currentTime = Clock.systemDefaultZone().millis();;
        expireTime=expireTime>currentTime?
                expireTime:currentTime+60*5*1000;
        effectiveTime=effectiveTime>=currentTime?
                effectiveTime:currentTime;
        Token.Header header=token.getHeader();
        header.setAlg("HS256");
        header.setTyp("JWT");
        Token.Payload payload =token.getPayload();
        payload.setIss(Project.USER.getProjectName());
        payload.setSud(Project.LOGIC.getProjectName());
        payload.setAud(user.getUserName());
        payload.setExp(expireTime);
        payload.setNbf(effectiveTime);
        payload.setIat(currentTime);
        //唯一标识
        payload.setJti(currentTime);
        //用户信息
        Token.TokenUserInfo tokenUserInfo= payload.getUserInfo();
        tokenUserInfo.setId(user.getId());
        tokenUserInfo.setUserName(user.getUserName());
        tokenUserInfo.setInfo(user.getInfo());
        tokenUserInfo.setRoleIds(user.getRoles().stream()
        .map(role -> {return String.valueOf(role.getId());})
        .reduce((roleStr1,roleStr2)->{return roleStr1+"-"+roleStr2;})
        .orElse(""));
        //个性化信息
        //TODO 提出
        Map<String,Object> data= Maps.newHashMap();
        data.put("userId",user.getId());
        data.put("userName",user.getUserName());
        payload.setData(data);
        //签名
        token.setSignature(creatSignForToken(token));
        return Optional.of(token);
    }
    public static boolean verifyToken(Token token){
       if(Objects.isNull(token)|| StringUtils.isEmpty(token.getSignature())){
           return false;
       }
       return creatSignForToken(token).equals(token.getSignature());
    }
    public static boolean verifyToken(String token){
        if(StringUtils.isEmpty(token)){
            return false;
        }
        String[] strArr=token.split(TOKEN_SPLIT);
        if(strArr.length!=3){
            return false;
        }
        return creatSignForToken(token).equals(strArr[2]);
    }
    private static String creatSignForToken(Token token){
        if(Objects.nonNull(token)){
            StringBuilder str=new StringBuilder();
            String headerStr= Base64Utils.encodeToString(JSON.toJSONString(token.getHeader()).getBytes());
            String payloadStr=Base64Utils.encodeToString(JSON.toJSONString(token.getPayload()).getBytes());
            str.append(headerStr).append(".").append(payloadStr).append(SECRET);
            //SHA256加盐散列
            return DigestUtils.sha256Hex(str.toString());
        }
        return "";
    }
    private static String creatSignForToken(String token){
        if(!StringUtils.isEmpty(token)){
            StringBuilder str=new StringBuilder();
            String[] strArr=token.split(TOKEN_SPLIT);
            if(strArr.length!=3){
                return str.toString();
            }
            String headerStr = strArr[0];
            String payloadStr= strArr[1];
            str.append(headerStr).append(".").append(payloadStr).append(SECRET);
            //SHA256加盐散列
            return DigestUtils.sha256Hex(str.toString());
        }
        return "";
    }
    public static String formatToken(Token token){
        if(Objects.isNull(token)){
            return "";
        }
        StringBuilder str=new StringBuilder();
        String headerStr= Base64Utils.encodeToString(JSON.toJSONString(token.getHeader()).getBytes());
        String payloadStr=Base64Utils.encodeToString(JSON.toJSONString(token.getPayload()).getBytes());
        String signature=creatSignForToken(token);
        return str.append(headerStr).append(".").append(payloadStr).append(".").append(signature).toString();
    }
    public static Token formatToken(String tokenStr){
        if(StringUtils.isEmpty(tokenStr)){
            return null;
        }
        String[] strArr=tokenStr.split(TOKEN_SPLIT);
        if(strArr.length!=3){
            return null;
        }
        String headerJson=new String(Base64Utils.decodeFromString(strArr[0]));
        String payloadJson=new String(Base64Utils.decodeFromString(strArr[1]));
        String signature=strArr[2];
        //TODO Q:直接反序列化不行？
        Token token=new Token();
        token.setSignature(signature);
        token.setHeader(JSON.parseObject(headerJson,Token.Header.class));
        token.setPayload(JSON.parseObject(payloadJson,Token.Payload.class));
        return token;
    }

}
