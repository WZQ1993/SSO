package com.ziqingwang.db;

import com.sun.glass.ui.Application;
import com.ziqingwang.domain.user.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by ziqingwang on 2017/3/6.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@Profile(value = "dev")
public class UserDbTest {
    @Autowired
    private UserRepository userRepository;
    @Test
    public void saveTest() throws Exception {
        User user=new User();
        user.setUserName("zip");
        user.setPassWord("pass");
        userRepository.save(user);
    }
}
