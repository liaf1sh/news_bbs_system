package com.lindong.domain;

import java.io.Serializable;
import java.util.Date;

public class SysWarn implements Serializable {
    private Integer id;

    private Integer uid;

    private String title;

    private String content;

    private Date w_time;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getW_time() {
        return w_time;
    }

    public void setW_time(Date w_time) {
        this.w_time = w_time;
    }
}