package com.ziqingwang.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * Created by ziqingwang on 2017/3/6.
 */
@RestController
@RequestMapping("/user")
public class UserController {
    Logger logger= LoggerFactory.getLogger(getClass());
    /**
     * 在 @PreAuthorize 中我们可以利用内建的 SPEL 表达式：比如 'hasRole()' 来决定哪些用户有权访问。
     * 需注意的一点是 hasRole 表达式认为每个角色名字前都有一个前缀 'ROLE_'。所以这里的 'ADMIN' 其实在
     * 数据库中存储的是 'ROLE_ADMIN' 。这个 @PreAuthorize 可以修饰Controller也可修饰Controller中的方法。
     **/
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    @ResponseBody
    @PostAuthorize("principal.id==(#id) or hasRole('ADMIN')")
    public String User(@PathVariable("id")long id) {
        //查询自己的或者为管理员均有权限
        return "success";
    }

}
