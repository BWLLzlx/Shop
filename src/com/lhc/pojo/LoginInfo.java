package com.lhc.pojo;

public class LoginInfo {
    private Integer id;
    private String ip;
    private String date;
    private String address;
    private String operation;
    private String role;
    private Integer roleId;

    @Override
    public String toString() {
        return "LoginInfo{" +
                "id=" + id +
                ", ip='" + ip + '\'' +
                ", date='" + date + '\'' +
                ", address='" + address + '\'' +
                ", operation='" + operation + '\'' +
                ", role='" + role + '\'' +
                ", roleId=" + roleId +
                '}';
    }

    public LoginInfo(Integer id, String ip, String date, String address, String operation, String role, Integer roleId) {
        this.id = id;
        this.ip = ip;
        this.date = date;
        this.address = address;
        this.operation = operation;
        this.role = role;
        this.roleId = roleId;
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

    public LoginInfo() {
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }
}
