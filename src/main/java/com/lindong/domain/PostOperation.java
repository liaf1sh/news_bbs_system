package com.lindong.domain;

import java.io.Serializable;

public class PostOperation implements Serializable {
    private Integer id;

    private Integer postId;

    private Integer uid;

    private Boolean isCompletion;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Boolean getIsCompletion() {
        return isCompletion;
    }

    public void setIsCompletion(Boolean isCompletion) {
        this.isCompletion = isCompletion;
    }
}