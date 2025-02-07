package com.lindong.domain;

import java.io.Serializable;
import java.util.Date;

public class PostShare implements Serializable {
    private Integer id;

    private Integer user_id;

    private Integer post_id;

    private String post_title;

    private Date share_time;

    private String plate_name;

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

    public Integer getPost_id() {
        return post_id;
    }

    public void setPost_id(Integer post_id) {
        this.post_id = post_id;
    }

    public String getPost_title() {
        return post_title;
    }

    public void setPost_title(String post_title) {
        this.post_title = post_title;
    }

    public Date getShare_time() {
        return share_time;
    }

    public void setShare_time(Date share_time) {
        this.share_time = share_time;
    }

    public String getPlate_name() {
        return plate_name;
    }

    public void setPlate_name(String plate_name) {
        this.plate_name = plate_name;
    }
}