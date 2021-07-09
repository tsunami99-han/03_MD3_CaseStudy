package dao;

import model.Comment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CommentDAO {

    SQLConnection connectionSQL = new SQLConnection();
    Connection connection = null;
    PreparedStatement statement = null;
    DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private final String All_COMMENT_QUERY= "select * from comment where post_id=? order by time";
    private final String COMMENT="insert into comment(post_id,user_id,content,time ) values (?,?,?,?) ; ";
    private final String EDIT_COMMENT="update comment set content=? where post_id = ? and user_id = ?";
    private final String DELETE_COMMENT= "delete from comment where post_id = ? and user_id = ?;";

    public List<Comment> findByPostID(int id) throws SQLException, ClassNotFoundException {
        List<Comment> list=new ArrayList<>();
        connection=connectionSQL.getConnection();
        statement= connection.prepareStatement(All_COMMENT_QUERY);
        statement.setInt(1,id);
        ResultSet resultSet=statement.executeQuery();
        while (resultSet.next()){
            int post_id = resultSet.getInt("post_id");
            int user_id = resultSet.getInt("user_id");
            String content = resultSet.getString("content");
            String timeString=resultSet.getString("time");
            LocalDateTime time=LocalDateTime.parse(timeString,formatter);
            list.add(new Comment(post_id,user_id,content,time));
        }
        return list;
    }
    public void addComment(Comment comment) throws SQLException, ClassNotFoundException {
        connection=connectionSQL.getConnection();
        statement= connection.prepareStatement(COMMENT);
        statement.setInt(1,comment.getPost_id());
        statement.setInt(2,comment.getUser_id());
        statement.setString(3,comment.getContent());
        statement.setString(4,comment.getTime().toString());
        statement.executeUpdate();
    }
}
