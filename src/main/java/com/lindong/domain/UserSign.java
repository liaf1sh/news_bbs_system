package com.lindong.domain;

import java.io.Serializable;
import java.util.Date;

public class UserSign implements Serializable {
    private Integer id;

    private Integer user_id;

    private String continuous_days;

    private Integer total_days;

    private Integer month_days;

    private Date last_sign_time;

    private String last_award;

    private String sign_status;

    private Integer sign_ranking;       //数据库中没有,计算排名新加的字段

    private  User user;

    private static final long serialVersionUID = 1L;

    public Integer getSign_ranking() {
        return sign_ranking;
    }

    public void setSign_ranking(Integer sign_ranking) {
        this.sign_ranking = sign_ranking;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getMonth_days() {
        return month_days;
    }

    public void setMonth_days(Integer month_days) {
        this.month_days = month_days;
    }

    public String getLast_award() {
        return last_award;
    }

    public void setLast_award(String last_award) {
        this.last_award = last_award;
    }

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

    public String getContinuous_days() {
        return continuous_days;
    }

    public void setContinuous_days(String continuous_days) {
        this.continuous_days = continuous_days;
    }

    public Integer getTotal_days() {
        return total_days;
    }

    public void setTotal_days(Integer total_days) {
        this.total_days = total_days;
    }

    public Date getLast_sign_time() {
        return last_sign_time;
    }

    public void setLast_sign_time(Date last_sign_time) {
        this.last_sign_time = last_sign_time;
    }

    public String getSign_status() {
        return sign_status;
    }

    public void setSign_status(String sign_status) {
        this.sign_status = sign_status;
    }
}