package model;

import java.time.LocalDateTime;

public class Comment {
    private int post_id;
    private int user_id;
    private String content;
    private LocalDateTime time;

    public Comment(int post_id, int user_id, String content, LocalDateTime time) {
        this.post_id = post_id;
        this.user_id = user_id;
        this.content = content;
        this.time = time;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public Comment() {
    }

    public int getPost_id() {
        return post_id;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
