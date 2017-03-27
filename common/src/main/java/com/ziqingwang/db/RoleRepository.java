package com.ziqingwang.db;

import com.ziqingwang.domain.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by 王梓青 on 2017/3/12.
 */
public interface RoleRepository extends JpaRepository<Role,Long> {
    @Query(value="select roleId from t_roles where userId=?1",nativeQuery = true)
    List<Integer> getRolesByUserId(long id);
}
