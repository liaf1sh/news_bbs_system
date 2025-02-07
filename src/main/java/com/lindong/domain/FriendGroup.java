package com.lindong.domain;

import java.io.Serializable;
import java.util.List;

public class FriendGroup implements Serializable {
    private Integer group_id;

    private String group_name;

    private Integer user_id;

    private List<UserFriend> userFriends;

    private static final long serialVersionUID = 1L;

    public List<UserFriend> getUserFriends() {
        return userFriends;
    }

    public void setUserFriends(List<UserFriend> userFriends) {
        this.userFriends = userFriends;
    }

    public Integer getGroup_id() {
        return group_id;
    }

    public void setGroup_id(Integer group_id) {
        this.group_id = group_id;
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }
}