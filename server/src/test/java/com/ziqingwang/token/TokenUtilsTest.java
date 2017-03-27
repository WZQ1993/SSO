package com.ziqingwang.token;

import com.ziqingwang.domain.user.User;
import com.ziqingwang.to.UserTO;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Clock;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

/**
 * Created by 王梓青 on 2017/3/7.
 */
public class TokenUtilsTest {
    Logger logger= LoggerFactory.getLogger(getClass());
    @Test
    public void creatTokenTest(){
        UserTO user=new UserTO();
        user.setId(1);
        user.setAge(1);
        user.setInfo("info");
        user.setUserName("userName");
        user.setPassWord("passWord");
        user.setPic("pic");
        user.setSchool("school");
        Instant expireTime=Instant.now().plus(1, ChronoUnit.HOURS);
        Instant effectiveTime=Instant.now().plus(30,ChronoUnit.MINUTES);
        Token token=TokenUtils.creatToken(user,expireTime.toEpochMilli(),effectiveTime.toEpochMilli()).get();
        logger.debug("sign:{}",token.getSignature());
        logger.debug("verify:{}",TokenUtils.verifyToken(token));
    }
    @Test
    public void test(){
        logger.debug(System.currentTimeMillis()+"");
        logger.debug(Clock.systemDefaultZone().millis()+"");
        logger.debug(Instant.now().toEpochMilli()+"");
    }
}
