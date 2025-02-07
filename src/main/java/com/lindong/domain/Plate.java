package com.lindong.domain;

import java.io.Serializable;
import java.util.List;

public class Plate implements Serializable {
    private Integer id;

    private String plate_name;

    private String plate_photo;

    private Integer theme;

    private Integer posts;

    private String collect_total;

    private String plate_vest;

    private String reserve1;

    private String reserve2;

    private Integer todayTotalTheme;

    private List<Post> postList;

    public Integer getTodayTotalTheme() {
        return todayTotalTheme;
    }

    public void setTodayTotalTheme(Integer todayTotalTheme) {
        this.todayTotalTheme = todayTotalTheme;
    }

    public List<Post> getPostList() {
        return postList;
    }

    public void setPostList(List<Post> postList) {
        this.postList = postList;
    }

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPlate_name() {
        return plate_name;
    }

    public void setPlate_name(String plate_name) {
        this.plate_name = plate_name;
    }

    public String getPlate_photo() {
        return plate_photo;
    }

    public void setPlate_photo(String plate_photo) {
        this.plate_photo = plate_photo;
    }

    public Integer getTheme() {
        return theme;
    }

    public void setTheme(Integer theme) {
        this.theme = theme;
    }

    public Integer getPosts() {
        return posts;
    }

    public void setPosts(Integer posts) {
        this.posts = posts;
    }

    public String getCollect_total() {
        return collect_total;
    }

    public void setCollect_total(String collect_total) {
        this.collect_total = collect_total;
    }

    public String getPlate_vest() {
        return plate_vest;
    }

    public void setPlate_vest(String plate_vest) {
        this.plate_vest = plate_vest;
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