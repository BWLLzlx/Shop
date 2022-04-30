package com.lhc.pojo;

public class Seller {
    private Integer id;
    private String name;
    private String password;
    private Integer salesVolume = 0;

    public Seller(Integer id, String name, String password, Integer salesVolume) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.salesVolume = salesVolume;
    }

    public Seller() {
    }

    @Override
    public String toString() {
        return "Seller{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", salesVolume=" + salesVolume +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getSalesVolume() {
        return salesVolume;
    }

    public void setSalesVolume(Integer salesVolume) {
        this.salesVolume = salesVolume;
    }
}
