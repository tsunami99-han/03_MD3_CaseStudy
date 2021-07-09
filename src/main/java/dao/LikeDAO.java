package dao;

import model.Like;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LikeDAO {
    SQLConnection connectionSQL = new SQLConnection();
    PreparedStatement preparedStatement = null;
    Connection connection = null;
    private String ADD_LIKE = "INSERT INTO `like`(user_id,post_id) VALUES(?,?)";
    ;
    private String DELETE_LIKE = "DELETE from `like` where user_id =? AND post_id=?";
    private String FIND_BY_POST_ID = "select * from `like` where post_id=?";

    public void addLike(Like like) throws SQLException, ClassNotFoundException {
        connection = connectionSQL.getConnection();
        preparedStatement = connection.prepareStatement(ADD_LIKE);
        preparedStatement.setInt(1, like.getUser_id());
        preparedStatement.setInt(2, like.getPost_id());
        preparedStatement.executeUpdate();
    }

    public void deleteLike(int user_id, int post_id) throws SQLException, ClassNotFoundException {
        connection = connectionSQL.getConnection();
        preparedStatement = connection.prepareStatement(DELETE_LIKE);
        preparedStatement.setInt(1, user_id);
        preparedStatement.setInt(2, post_id);
        preparedStatement.executeUpdate();
    }

    public List<Like> findByPostID(int post_id) throws SQLException, ClassNotFoundException {
        List<Like> list = new ArrayList<>();
        connection = connectionSQL.getConnection();
        preparedStatement = connection.prepareStatement(FIND_BY_POST_ID);
        preparedStatement.setInt(1,post_id);
        ResultSet resultSet=preparedStatement.executeQuery();
        while (resultSet.next()){
            int userid = resultSet.getInt("user_id");
            list.add(new Like(post_id,userid));
        }
        return list;
    }
}
