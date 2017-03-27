package com.ziqingwang.utils;

import com.google.common.collect.Maps;
import com.ziqingwang.domain.user.Role;
import org.assertj.core.util.Lists;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by 王梓青 on 2017/3/12.
 */
public class RoleUtils {
    private RoleUtils(){

    }
    private static  final Map<Integer,Role> roleMap= Maps.newHashMap();

    public static  void init(){
        roleMap.put(1,new Role(1,"ROLE_USER","USER"));
        roleMap.put(2,new Role(2,"ROLE_ADMIN","ADMIN"));
    }

    public static Role getRoleById(int id){
        if(roleMap.isEmpty()){
            synchronized (RoleUtils.class){
                if (roleMap.isEmpty()){
                    init();
                }
            }
        }
        return roleMap.get(id);
    }
    public static List<Role> getRoles(String roleIds) {
        if(StringUtils.isEmpty(roleIds)){
            return Lists.emptyList();
        }
        return Stream.of(roleIds.split("-"))
                .map(idStr->{return Integer.valueOf(idStr);})
                .map(RoleUtils::getRoleById)
                .collect(Collectors.toList());
    }
}
