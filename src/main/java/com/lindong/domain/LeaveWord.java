package com.lindong.domain;

import java.io.Serializable;
import java.util.Date;

public class LeaveWord implements Serializable {
    private Integer id;

    private Integer leave_u_id;

    private String leave_name;

    private String leave_photo;

    private Integer to_u_id;

    private Date leave_time;

    private String leave_content;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLeave_u_id() {
        return leave_u_id;
    }

    public void setLeave_u_id(Integer leave_u_id) {
        this.leave_u_id = leave_u_id;
    }

    public String getLeave_name() {
        return leave_name;
    }

    public void setLeave_name(String leave_name) {
        this.leave_name = leave_name;
    }

    public String getLeave_photo() {
        return leave_photo;
    }

    public void setLeave_photo(String leave_photo) {
        this.leave_photo = leave_photo;
    }

    public Integer getTo_u_id() {
        return to_u_id;
    }

    public void setTo_u_id(Integer to_u_id) {
        this.to_u_id = to_u_id;
    }

    public Date getLeave_time() {
        return leave_time;
    }

    public void setLeave_time(Date leave_time) {
        this.leave_time = leave_time;
    }

    public String getLeave_content() {
        return leave_content;
    }

    public void setLeave_content(String leave_content) {
        this.leave_content = leave_content;
    }
}