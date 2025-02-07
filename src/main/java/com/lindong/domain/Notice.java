package com.lindong.domain;

import java.io.Serializable;
import java.util.Date;

public class Notice implements Serializable {
    private Integer n_id;

    private String n_title;

    private Date n_time;

    private  String another_name;

    private String n_in_plate_name;

    private Integer n_uid;

    private String n_content;

    private Integer n_plate_id;

    private static final long serialVersionUID = 1L;

    public Integer getN_plate_id() {
        return n_plate_id;
    }

    public void setN_plate_id(Integer n_plate_id) {
        this.n_plate_id = n_plate_id;
    }

    public String getAnother_name() {
        return another_name;
    }

    public void setAnother_name(String another_name) {
        this.another_name = another_name;
    }

    public Integer getN_id() {
        return n_id;
    }

    public void setN_id(Integer n_id) {
        this.n_id = n_id;
    }

    public String getN_title() {
        return n_title;
    }

    public void setN_title(String n_title) {
        this.n_title = n_title;
    }

    public Date getN_time() {
        return n_time;
    }

    public void setN_time(Date n_time) {
        this.n_time = n_time;
    }

    public String getN_in_plate_name() {
        return n_in_plate_name;
    }

    public void setN_in_plate_name(String n_in_plate_name) {
        this.n_in_plate_name = n_in_plate_name;
    }

    public Integer getN_uid() {
        return n_uid;
    }

    public void setN_uid(Integer n_uid) {
        this.n_uid = n_uid;
    }

    public String getN_content() {
        return n_content;
    }

    public void setN_content(String n_content) {
        this.n_content = n_content;
    }
}