package com.zqh.hessian.bo;

/**
 * Created by OrangeKiller on 2017/2/21.
 */

import java.io.Serializable;
import java.util.Date;

public class Person implements Serializable {
    private static final long serialVersionUID = 8268991521882061845L;

    private int id;
    private String name ;
    private Long phone ;
    private boolean gender;
    private double height;
    private float weight;
    private String[] address;
    private Date birthday;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Long getPhone() {
        return phone;
    }
    public void setPhone(Long phone) {
        this.phone = phone;
    }
    public boolean isGender() {
        return gender;
    }
    public void setGender(boolean gender) {
        this.gender = gender;
    }
    public double getHeight() {
        return height;
    }
    public void setHeight(double height) {
        this.height = height;
    }
    public float getWeight() {
        return weight;
    }
    public void setWeight(float weight) {
        this.weight = weight;
    }
    public String[] getAddress() {
        return address;
    }
    public void setAddress(String[] address) {
        this.address = address;
    }
    public Date getBirthday() {
        return birthday;
    }
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String toString(){
        return "name:" + this.name + " phone:" + this.phone;
    }
}