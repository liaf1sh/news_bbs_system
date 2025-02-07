package com.lindong.domain;

import java.io.Serializable;

public class Experience implements Serializable {
    private Integer id;

    private Integer user_id;

    private Integer user_experience;

    private String time;

    private Integer total_experience;

    private String reserve1;

    private String reserve2;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getUser_experience() {
        return user_experience;
    }

    public void setUser_experience(Integer user_experience) {
        this.user_experience = user_experience;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getTotal_experience() {
        return total_experience;
    }

    public void setTotal_experience(Integer total_experience) {
        this.total_experience = total_experience;
    }

    public String getReserve1() {
        return reserve1;
    }

    public void setReserve1(String reserve1) {
        this.reserve1 = reserve1;
    }

    public String getReserve2() {
        return reserve2;
    }

    public void setReserve2(String reserve2) {
        this.reserve2 = reserve2;
    }
}