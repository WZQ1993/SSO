package com.ziqingwang.service;

import com.ziqingwang.db.RoleRepository;
import com.ziqingwang.db.UserRepository;
import com.ziqingwang.domain.user.Role;
import com.ziqingwang.domain.user.User;
import com.ziqingwang.to.UserTO;
import com.ziqingwang.utils.RoleUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created by 王梓青 on 2017/3/6.
 */
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    public UserTO findByUserName(String username){
        User user = userRepository.findByUsername(username);
        return  tranferUser(user);
    }
    public UserTO saveUser(User userToAdd){
        return tranferUser(userRepository.save(userToAdd));

    }
    public UserTO tranferUser(User user){
        if (Objects.isNull(user)) {
            return null;
        } else {
            List<Role> roles=roleRepository.getRolesByUserId(user.getId()).stream()
                    .map(RoleUtils::getRoleById)
                    .collect(Collectors.toList());
            UserTO userTO=new UserTO();
            BeanUtils.copyProperties(user,userTO);
            userTO.setRoles(roles);
            return userTO;
        }
    }
}
