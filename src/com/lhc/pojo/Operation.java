package com.lhc.pojo;

import java.util.Date;

public class Operation {
    private Integer id;
    private String ip;
    private String date;
    private String role;
    private Integer roleId;
    private String operate;
    private String target;

    public Operation(Integer id, String ip, String date, String role, Integer roleId, String operate, String target) {
        this.id = id;
        this.ip = ip;
        this.date = date;
        this.role = role;
        this.roleId = roleId;
        this.operate = operate;
        this.target = target;
    }

    public Operation() {
    }

    @Override
    public String toString() {
        return "Operation{" +
                "id=" + id +
                ", ip='" + ip + '\'' +
                ", date=" + date +
                ", role='" + role + '\'' +
                ", roleId=" + roleId +
                ", operate='" + operate + '\'' +
                ", target='" + target + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getOperate() {
        return operate;
    }

    public void setOperate(String operate) {
        this.operate = operate;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }
}
