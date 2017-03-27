package com.ziqingwang.enumeration;

/**
 * 这块可以认为是服务发现
 * 尝试使用zookeeper注册服务
 * Created by 王梓青 on 2017/3/6.
 */
public enum Project {
    USER("user",8001,1),LOGIC("logic",8002,2);
    private String projectName;
    private int port;
    private int index;
    private Project(String projectName,int index,int port){
        this.index=index;
        this.projectName=projectName;
        this.port=port;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
