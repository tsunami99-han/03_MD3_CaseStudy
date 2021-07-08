package model;

public class Like {
    private int post_id;
    private int user_id;

    public Like(int post_id, int user_id) {
        this.post_id = post_id;
        this.user_id = user_id;
    }

    public Like() {
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
}
