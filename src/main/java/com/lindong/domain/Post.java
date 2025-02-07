package com.lindong.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Post implements Serializable {
    private Integer id;

    private String title;

    private Integer user_id;

    private Integer browse_count;

    private Integer reply_count;

    private Date publish_time;

    private Boolean first_post;

    private String post_status;

    private String post_heat;

    private Integer share_count;

    private String post_grade;

    private String post_collect;

    private String post_top;

    private String post_tread;

    private String post_author;

    private Integer plate_id;

    private String last_reply;

    private Date last_reply_time;

    private String content;

    private String total_integral;        //用户总积分,表中没有,为了简便映射新加字段

    private String picture;        //用户头像,表中没有,为了简便映射新加字段

    private String online_status;        //用户在线状态,表中没有,为了简便映射新加字段

    private Plate plate;

    private List<PostDetails> listPosts;    //一对多关系映射

    private Integer themeCount;       //用户主题数

    private Integer postsCount;       //用户帖子数

    private Integer total_experience;       //用户总经验

    private Grade grade;        //用户等级类

    private String plate_name;

    private PostShare postShare; //用户分享数据

    private static final long serialVersionUID = 1L;


    public PostShare getPostShare() {
        return postShare;
    }

    public void setPostShare(PostShare postShare) {
        this.postShare = postShare;
    }

    public String getPlate_name() {
        return plate_name;
    }

    public void setPlate_name(String plate_name) {
        this.plate_name = plate_name;
    }

    public Date getLast_reply_time() {
        return last_reply_time;
    }

    public void setLast_reply_time(Date last_reply_time) {
        this.last_reply_time = last_reply_time;
    }

    public Plate getPlate() {
        return plate;
    }

    public void setPlate(Plate plate) {
        this.plate = plate;
    }

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

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getOnline_status() {
        return online_status;
    }

    public void setOnline_status(String online_status) {
        this.online_status = online_status;
    }

    public String getTotal_integral() {
        return total_integral;
    }

    public void setTotal_integral(String total_integral) {
        this.total_integral = total_integral;
    }

    public List<PostDetails> getListPosts() {
        return listPosts;
    }

    public void setListPosts(List<PostDetails> listPosts) {
        this.listPosts = listPosts;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getBrowse_count() {
        return browse_count;
    }

    public void setBrowse_count(Integer browse_count) {
        this.browse_count = browse_count;
    }

    public Integer getReply_count() {
        return reply_count;
    }

    public void setReply_count(Integer reply_count) {
        this.reply_count = reply_count;
    }

    public Date getPublish_time() {
        return publish_time;
    }

    public void setPublish_time(Date publish_time) {
        this.publish_time = publish_time;
    }

    public Boolean getFirst_post() {
        return first_post;
    }

    public void setFirst_post(Boolean first_post) {
        this.first_post = first_post;
    }

    public String getPost_status() {
        return post_status;
    }

    public void setPost_status(String post_status) {
        this.post_status = post_status;
    }

    public String getPost_heat() {
        return post_heat;
    }

    public void setPost_heat(String post_heat) {
        this.post_heat = post_heat;
    }

    public Integer getShare_count() {
        return share_count;
    }

    public void setShare_count(Integer share_count) {
        this.share_count = share_count;
    }

    public String getPost_grade() {
        return post_grade;
    }

    public void setPost_grade(String post_grade) {
        this.post_grade = post_grade;
    }

    public String getPost_collect() {
        return post_collect;
    }

    public void setPost_collect(String post_collect) {
        this.post_collect = post_collect;
    }

    public String getPost_top() {
        return post_top;
    }

    public void setPost_top(String post_top) {
        this.post_top = post_top;
    }

    public String getPost_tread() {
        return post_tread;
    }

    public void setPost_tread(String post_tread) {
        this.post_tread = post_tread;
    }

    public String getPost_author() {
        return post_author;
    }

    public void setPost_author(String post_author) {
        this.post_author = post_author;
    }

    public Integer getPlate_id() {
        return plate_id;
    }

    public void setPlate_id(Integer plate_id) {
        this.plate_id = plate_id;
    }

    public String getLast_reply() {
        return last_reply;
    }

    public void setLast_reply(String last_reply) {
        this.last_reply = last_reply;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}