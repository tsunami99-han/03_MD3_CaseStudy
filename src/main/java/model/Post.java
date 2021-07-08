package model;

import java.time.LocalDateTime;

public class Post {
    private int id;
    private int user_id;
    private LocalDateTime time;
    private String title;
    private String content;
    private String status;
    private int likeQuantity=0;
    private int commentQuantity=0;

    public Post() {
    }

    public Post(int id, int user_id, LocalDateTime time, String title, String content, String status) {
        this.id = id;
        this.user_id = user_id;
        this.time = time;
        this.title = title;
        this.content = content;
        this.status = status;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public int getLikeQuantity() {
        return likeQuantity;
    }

    public void setLikeQuantity(int likeQuantity) {
        this.likeQuantity = likeQuantity;
    }

    public int getCommentQuantity() {
        return commentQuantity;
    }

    public void setCommentQuantity(int commentQuantity) {
        this.commentQuantity = commentQuantity;
    }



    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public Post(int id, int user_id, LocalDateTime time, String title, String content, String status, int likeQuantity, int commentQuantity) {
        this.id = id;
        this.user_id = user_id;
        this.time = time;
        this.title = title;
        this.content = content;
        this.status = status;
        this.likeQuantity = likeQuantity;
        this.commentQuantity = commentQuantity;
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


}
