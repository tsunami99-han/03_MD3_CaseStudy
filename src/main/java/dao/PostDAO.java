package dao;

import model.Post;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class PostDAO implements IDAO<Post>{
    SQLConnection connectionSQL = new SQLConnection();
    Connection connection = null;
    PreparedStatement statement = null;
private final String FIND_ALL_QUERY = "select * from post";
private final String FIND_BY_ID_QUERY = "select * from post where id = ?;";
private final String FIND_BY_NAME_QUERY = " select p.id,p.user_id,p.title,p.content,p.time,p.status,p.likequantity,p.commentquantity from post p join user u on p.user_id=u.id where u.fullname like ?; ";
private final String INSERT_POST_QUERY = "INSERT INTO post (`user_id`, `title`, `content`, `time`, `status`) VALUES (?,?,?,?,?);";
    @Override
    public List<Post> findAll() throws SQLException, ClassNotFoundException {
        List<Post> list=new ArrayList<>();
        connection=connectionSQL.getConnection();
        statement=connection.prepareStatement(FIND_ALL_QUERY);
        ResultSet resultSet=statement.executeQuery();
        while (resultSet.next()){
            int id=resultSet.getInt("id");
            int user_id=resultSet.getInt("user_id");
            String timeString=resultSet.getString("time");
            DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime time=LocalDateTime.parse(timeString,formatter);
            String title =resultSet.getString("title");
            String content = resultSet.getString("content");
            String status = resultSet.getString("status");
            int likeQuantity = resultSet.getInt("likequantity");
            int commentQuantity=resultSet.getInt("commentquantity");
            list.add(new Post(id,user_id,time,title,content,status,likeQuantity,commentQuantity));
        }
        return list;
    }

    @Override
    public Post findById(int id) throws SQLException, ClassNotFoundException {
        connection=connectionSQL.getConnection();
        statement=connection.prepareStatement(FIND_BY_ID_QUERY);
        statement.setInt(1,id);
        ResultSet resultSet=statement.executeQuery();
        Post post= null;
        while (resultSet.next()){
            int user_id=resultSet.getInt("user_id");
            String timeString=resultSet.getString("time");
            DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime time=LocalDateTime.parse(timeString,formatter);
            String title =resultSet.getString("title");
            String content = resultSet.getString("content");
            String status = resultSet.getString("status");
            int likeQuantity = resultSet.getInt("likequantity");
            int commentQuantity=resultSet.getInt("commentquantity");
          post=new Post(id,user_id,time,title,content,status,likeQuantity,commentQuantity);
        }
        return post;
    }

    @Override
    public List<Post> findByName(String name) {

        return null;
    }

    public List<Post> findByUserName(String name) throws SQLException, ClassNotFoundException {
    connection=connectionSQL.getConnection();
    statement= connection.prepareStatement(FIND_BY_NAME_QUERY);
    statement.setString(1,"%"+name+"%");
    List<Post> list =new ArrayList<>();
    ResultSet resultSet=statement.executeQuery();
        while (resultSet.next()){
            int id=resultSet.getInt("id");
            int user_id=resultSet.getInt("user_id");
            String timeString=resultSet.getString("time");
            DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime time=LocalDateTime.parse(timeString,formatter);
            String title =resultSet.getString("title");
            String content = resultSet.getString("content");
            String status = resultSet.getString("status");
            int likeQuantity = resultSet.getInt("likequantity");
            int commentQuantity=resultSet.getInt("commentquantity");
           list.add(new Post(id,user_id,time,title,content,status,likeQuantity,commentQuantity));
        }
        return list;}
    @Override
    public void add(Post post) throws SQLException, ClassNotFoundException {
        connection=connectionSQL.getConnection();
        statement= connection.prepareStatement(INSERT_POST_QUERY);
        statement.setInt(1,post.getUser_id());
        statement.setString(2,post.getTitle());
        statement.setString(3,post.getContent());
        statement.setString(4,post.getTime().toString());
        statement.setString(5,post.getStatus());
        statement.executeUpdate();
    }

    @Override
    public void edit(int id, Post post) throws SQLException, ClassNotFoundException {

    }

    @Override
    public void delete(int id) {

    }
}
