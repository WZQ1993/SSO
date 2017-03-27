package com.ziqingwang.db;

import com.ziqingwang.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by ziqingwang on 2017/3/6.
 */
public interface UserRepository extends JpaRepository<User,Long> {
    @Query(value="select * from t_user where user_name=?1 and pass_word=?2",nativeQuery = true)
    User findByUsernameAndPassword(String userName, String passWord);

    @Query(value="select * from t_user where user_name=?1",nativeQuery = true)
    User findByUsername(String userName);
}
