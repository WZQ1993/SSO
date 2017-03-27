package com.ziqingwang.utils;

import java.util.Map;

/**
 * Created by 王梓青 on 2017/3/6.
 */
public class Token {
    private Header header = new Header();
    private Payload payload = new Payload();
    private String signature;

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public Payload getPayload() {
        return payload;
    }

    public void setPayload(Payload payload) {
        this.payload = payload;
    }

    public static class Header {
        //声明类型，这里是jwt
        private String typ = "jwt";
        //声明加密的算法 通常直接使用 HMAC SHA256
        private String alg;

        public String getTyp() {
            return typ;
        }

        public void setTyp(String typ) {
            this.typ = typ;
        }

        public String getAlg() {
            return alg;
        }

        public void setAlg(String alg) {
            this.alg = alg;
        }
    }

    public static class Payload {
        //jwt签发者
        private String iss;
        //jwt所面向的用户
        private String aud;
        //接收jwt的一方
        private String sud;
        //jwt的过期时间，这个过期时间必须要大于签发时间
        private long exp;
        //定义在什么时间之前，该jwt都是不可用的.
        private long nbf;
        // jwt的签发时间
        private long iat;
        // jwt的唯一身份标识，主要用来作为一次性token,从而回避重放攻击。
        private long jti;

        private TokenUserInfo userInfo=new TokenUserInfo();
        //其他数据
        private Map<String,Object> data;

        public Map<String, Object> getData() {
            return data;
        }

        public void setData(Map<String, Object> data) {
            this.data = data;
        }

        public String getIss() {
            return iss;
        }

        public void setIss(String iss) {
            this.iss = iss;
        }

        public String getAud() {
            return aud;
        }

        public void setAud(String aud) {
            this.aud = aud;
        }

        public String getSud() {
            return sud;
        }

        public void setSud(String sud) {
            this.sud = sud;
        }

        public long getExp() {
            return exp;
        }

        public void setExp(long exp) {
            this.exp = exp;
        }

        public long getNbf() {
            return nbf;
        }

        public void setNbf(long nbf) {
            this.nbf = nbf;
        }

        public long getIat() {
            return iat;
        }

        public void setIat(long iat) {
            this.iat = iat;
        }

        public long getJti() {
            return jti;
        }

        public void setJti(long jti) {
            this.jti = jti;
        }

        public TokenUserInfo getUserInfo() {
            return userInfo;
        }

        public void setUserInfo(TokenUserInfo userInfo) {
            this.userInfo = userInfo;
        }
    }

    public static class TokenUserInfo{
        private long id;
        private String userName;
        private String info;
        private String roleIds;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        public String getRoleIds() {
            return roleIds;
        }

        public void setRoleIds(String roleIds) {
            this.roleIds = roleIds;
        }

    }
}
