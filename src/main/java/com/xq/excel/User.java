package com.xq.excel;

import java.util.Date;

/**
 * @author zxq
 */
public class User {

    private Integer id;
    private String name;
    private String address;
    private Integer sex;
    private Date birth;

    public User() {
        // TODO Auto-generated constructor stub
    }

    public User(Integer id, String name, String address, Integer sex, Date birth) {
        super();
        this.id = id;
        this.name = name;
        this.address = address;
        this.sex = sex;
        this.birth = birth;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

}
