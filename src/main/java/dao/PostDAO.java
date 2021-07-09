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
    DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private final String FIND_ALL_QUERY = "select * from post order by time desc";
    private final String FIND_BY_ID_QUERY = "select * from post where id = ?;";
    private final String FIND_BY_NAME_QUERY = " select p.id,p.user_id,p.title,p.content,p.time,p.status,p.likequantity,p.commentquantity from post p join user u on p.user_id=u.id where u.fullname like ?; ";
    private final String FIND_BY_TITLE_QUERY = "select * from post where title like ? ";
    private final String INSERT_POST_UPDATE = "INSERT INTO post (`user_id`, `title`, `content`, `time`, `status`) VALUES (?,?,?,?,?);";
    private final String UPDATE_BY_ID_UPDATE = "UPDATE post SET title = ?, content = ?, `status` = ?WHERE (id = ?); ";
    private final String DELETE_COMMENT_BY_POSTID =" delete from comment where post_id = ?";
    private final String DELETE_POST_BY_ID = "delete from post where id=?";
    private final String DELETE_LIKE_BY_POSTID =" delete from like where post_id= ?";
    private final String COMMENT_QUANTITY_UPDATE= "update post set commentquantity = ? where id = ?";
    private final String COMMENT_QUANTITY_QUERY = "select commentquantity from post where id=?";
    private final String TOP5_LIKE_QUERY ="";
    private final String TOP5_COMMENT_QUERY ="";

    public void commentUpdateQuery(int id) throws SQLException, ClassNotFoundException {
        int quantity=commentQuantity(id);
        ++quantity;
        connection=connectionSQL.getConnection();
        statement=connection.prepareStatement(COMMENT_QUANTITY_UPDATE);
        statement.setInt(1,quantity);
        statement.setInt(2,id);
        statement.executeUpdate();
    }
    public int commentQuantity(int id) throws SQLException, ClassNotFoundException {
        connection=connectionSQL.getConnection();
        statement=connection.prepareStatement(COMMENT_QUANTITY_QUERY);
        statement.setInt(1,id);
        ResultSet resultSet=statement.executeQuery();
        while (resultSet.next()){
            int quantity=resultSet.getInt("commentquantity");
            return quantity;
        }
        return 0;
    }
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
    public List<Post> findByTitle(String title) throws SQLException, ClassNotFoundException {
        List<Post> list=new ArrayList<>();
        connection=connectionSQL.getConnection();
        statement= connection.prepareStatement(FIND_BY_TITLE_QUERY);
        statement.setString(1,"%"+title+"%");
        ResultSet resultSet=statement.executeQuery();
        while (resultSet.next()){
            int id=resultSet.getInt("id");
            int user_id=resultSet.getInt("user_id");
            String timeString=resultSet.getString("time");
            DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime time=LocalDateTime.parse(timeString,formatter);
            title =resultSet.getString("title");
            String content = resultSet.getString("content");
            String status = resultSet.getString("status");
            int likeQuantity = resultSet.getInt("likequantity");
            int commentQuantity=resultSet.getInt("commentquantity");
            list.add(new Post(id,user_id,time,title,content,status,likeQuantity,commentQuantity));
        }

        return list;
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
        statement= connection.prepareStatement(INSERT_POST_UPDATE);
        statement.setInt(1,post.getUser_id());
        statement.setString(2,post.getTitle());
        statement.setString(3,post.getContent());
        statement.setString(4,post.getTime().toString());
        statement.setString(5,post.getStatus());
        statement.executeUpdate();
    }

    @Override
    public void edit(int id, Post post) throws SQLException, ClassNotFoundException {
    connection=connectionSQL.getConnection();
    statement= connection.prepareStatement(UPDATE_BY_ID_UPDATE);
    statement.setString(1,post.getTitle());
    statement.setString(2,post.getContent());
    statement.setString(3,post.getStatus());
    statement.setInt(4,id);
    statement.executeUpdate();
    }

    @Override
    public void delete(int id) throws SQLException, ClassNotFoundException {
        deleteLike(id);
        deleteComment(id);
    connection=connectionSQL.getConnection();
    statement= connection.prepareStatement(DELETE_POST_BY_ID);
    statement.setInt(1,id);
    statement.executeUpdate();
    }
    public void deleteLike(int id) throws SQLException, ClassNotFoundException {
    connection=connectionSQL.getConnection();
    statement= connection.prepareStatement(DELETE_LIKE_BY_POSTID);
    statement.setInt(1,id);
    statement.executeUpdate();
    }
    public void deleteComment(int id) throws SQLException, ClassNotFoundException {
        connection=connectionSQL.getConnection();
        statement= connection.prepareStatement(DELETE_COMMENT_BY_POSTID);
        statement.setInt(1,id);
        statement.executeUpdate();
    }
}
