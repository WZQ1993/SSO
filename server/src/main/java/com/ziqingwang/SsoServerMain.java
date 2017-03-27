package com.ziqingwang;

import org.slf4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <br>启动命令参数：
 * --spring.profiles.active=dev --spring.configuration.location=../configuration/ --spring.configuration.name=db,application
 *<br/>
 * Created by 王梓青 on 2017/2/22.
 */
@SpringBootApplication
public class SsoServerMain {
    private static Logger logger= org.slf4j.LoggerFactory.getLogger(SsoServerMain.class);
    public static void main(String[] args){
        SpringApplication.run(SsoServerMain.class,args);
    }
}
