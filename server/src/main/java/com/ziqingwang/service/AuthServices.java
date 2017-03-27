package com.ziqingwang.service;

import com.ziqingwang.db.UserRepository;
import com.ziqingwang.domain.user.User;
import com.ziqingwang.to.UserTO;
import com.ziqingwang.utils.Token;
import com.ziqingwang.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

/**
 * Created by 王梓青 on 2017/3/12.
 */
@Service
public class AuthServices {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    public UserTO register(User userToAdd){
        return userService.saveUser(userToAdd);
    }
    public Token login(String userName, String passWord){
        UserTO userTO=loadUser(userName,passWord);
        if(Objects.nonNull(userTO)){
            return TokenUtils.creatToken(userTO, Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli(),0).orElse(null);
        }
        return null;
    }

    private UserTO loadUser(String userName,String passWord){
        User user=userRepository.findByUsernameAndPassword(userName,passWord);
        return  userService.tranferUser(user);
    }
}
