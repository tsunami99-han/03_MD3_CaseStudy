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
private final String FIND_ALL = "select * from post";


    @Override
    public List<Post> findAll() throws SQLException, ClassNotFoundException {
        List<Post> list=new ArrayList<>();
        connection=connectionSQL.getConnection();
        statement=connection.prepareStatement(FIND_ALL);
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
    public Post findById(int id) {
        return null;
    }

    @Override
    public List<Post> findByName(String name) {
        return null;
    }

    @Override
    public void add(Post post) {

    }

    @Override
    public void edit(int id, Post post) {

    }

    @Override
    public void delete(int id) {

    }
}
