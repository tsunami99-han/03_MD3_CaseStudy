package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class LikeDAO {
    SQLConnection connectionSQL = new SQLConnection();
    Connection connection = null;
    PreparedStatement statement = null;

    private final String LIKE = "insert into like values (?,?)";
    private final String UNLIKE="delete from like where post_id= ? and user_id=?";

}
