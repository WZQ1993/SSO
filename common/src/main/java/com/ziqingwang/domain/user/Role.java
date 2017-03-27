package com.ziqingwang.domain.user;

import javax.persistence.*;

/**
 * Created by ziqingwang on 2016/10/13.
 */
@Entity
@Table(name = "t_role")
public final class Role {
    @Id
    @GeneratedValue
    private long id;
    @Column(nullable = false)
    private String roleName;
    @Column
    private String des;

    public Role(long id,String roleName,String des){
        this.id=id;
        this.roleName=roleName;
        this.des=des;
    }
    public String getRoleName() {
        return roleName;
    }

    public long getId() {
        return id;
    }

    public String getDes() {
        return des;
    }

}
