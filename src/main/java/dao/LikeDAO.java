package dao;

import model.Like;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LikeDAO {
    SQLConnection sqlConnection = new SQLConnection();
    private final String NUMBER_LIKE = "SELECT COUNT(*) FROM like Where post_id=?;";
    private String ADD_LIKE = "INSERT INTO like VALUES(?,?)";;
    private String DELETE_LIKE = "DELETE from user where user_id =? AND post_id=?";

    public int number(int id) throws SQLException, ClassNotFoundException {
        Connection connection=sqlConnection.getConnection();
        PreparedStatement preparedStatement=connection.prepareStatement(NUMBER_LIKE);
        preparedStatement.setInt(1,id);
        ResultSet resultSet=preparedStatement.executeQuery();
        int count=resultSet.getRow();
        return count;
    }
    public void addLike(Like like)throws SQLException, ClassNotFoundException{
        Connection connection=sqlConnection.getConnection();
        PreparedStatement preparedStatement=connection.prepareStatement(ADD_LIKE);
        preparedStatement.setInt(1,like.getPost_id());
        preparedStatement.setInt(1,like.getUser_id());
        preparedStatement.executeUpdate();
    }
    public void deleteLike(int id)throws SQLException, ClassNotFoundException{
        Connection connection=sqlConnection.getConnection();
        PreparedStatement preparedStatement=connection.prepareStatement(DELETE_LIKE);
        preparedStatement.setInt(1,id);
        preparedStatement.executeUpdate();
    }
}
