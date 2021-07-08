package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class CommentDAO {

    SQLConnection connectionSQL = new SQLConnection();
    Connection connection = null;
    PreparedStatement statement = null;

    private final String COMMENT="insert into comment values (?,?,?,?) ; ";
    private final String EDIT_COMMENT="update comment set content=? where post_id = ? and user_id = ?";
    private final String DELETE_COMMENT= "delete from comment where post_id = ? and user_id = ?;";

}
