package com.lindong.domain;

import java.io.Serializable;
import java.util.Date;

public class PostDetails implements Serializable {
    private Integer pid;

    private Integer pd_uid;

    private Integer post_id;

    private String seat;

    private Date reply_time;

    private String pd_content;

    private String pd_nickname;        //用户昵称,表中没有,为了简便映射新加字段

    private String pd_picture;        //用户头像,表中没有,为了简便映射新加字段

    private String pd_total_integral;        //用户总积分,表中没有,为了简便映射新加字段

    private String pd_online_status;        //用户在线状态,表中没有,为了简便映射新加字段

    private Integer themeCount;       //用户主题数

    private Integer postsCount;       //用户帖子数

    private Integer total_experience;       //用户总经验

    private Grade grade;        //用户等级类

    private static final long serialVersionUID = 1L;

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public Integer getThemeCount() {
        return themeCount;
    }

    public void setThemeCount(Integer themeCount) {
        this.themeCount = themeCount;
    }

    public Integer getPostsCount() {
        return postsCount;
    }

    public void setPostsCount(Integer postsCount) {
        this.postsCount = postsCount;
    }

    public Integer getTotal_experience() {
        return total_experience;
    }

    public void setTotal_experience(Integer total_experience) {
        this.total_experience = total_experience;
    }

    public String getPd_picture() {
        return pd_picture;
    }

    public void setPd_picture(String pd_picture) {
        this.pd_picture = pd_picture;
    }

    public String getPd_nickname() {
        return pd_nickname;
    }

    public void setPd_nickname(String pd_nickname) {
        this.pd_nickname = pd_nickname;
    }

    public String getPd_total_integral() {
        return pd_total_integral;
    }

    public void setPd_total_integral(String pd_total_integral) {
        this.pd_total_integral = pd_total_integral;
    }

    public String getPd_online_status() {
        return pd_online_status;
    }

    public void setPd_online_status(String pd_online_status) {
        this.pd_online_status = pd_online_status;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getPd_uid() {
        return pd_uid;
    }

    public void setPd_uid(Integer pd_uid) {
        this.pd_uid = pd_uid;
    }

    public Integer getPost_id() {
        return post_id;
    }

    public void setPost_id(Integer post_id) {
        this.post_id = post_id;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public Date getReply_time() {
        return reply_time;
    }

    public void setReply_time(Date reply_time) {
        this.reply_time = reply_time;
    }

    public String getPd_content() {
        return pd_content;
    }

    public void setPd_content(String pd_content) {
        this.pd_content = pd_content;
    }
}