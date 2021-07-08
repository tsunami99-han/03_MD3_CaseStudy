package dao;

import model.User;

import javax.swing.text.DateFormatter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements IDAO<User> {
    SQLConnection sqlConnection = new SQLConnection();
    private final String FIND_ALL_QUERY = "Select *from user";
    private String ADD_QUERY = "INSERT INTO user(`username`, `password`, `fullname`, `dateofbirth`, `address`, `desc`, `image`, `role`) VALUES(?,?,?,?,?,?,?,?)";
    private String FINF_BY_ID_ADD_QUERY = "Select *from user where id=?";
    private String FINF_BY_NAME_ADD_QUERY = "Select *from user where fullname like ?";
    private String FINF_BY_ADDRESS_ADD_QUERY = "Select *from user where address like ?";
    private String EDIT_QUERY = "UPDATE user set username=?,password=?,fullname=?,dateofbirth=?,address=?,`desc`=?,image=?,role=? where id =?";
    private String DELETE_QUERY = "DELETE from user where id =?";

    @Override
    public List<User> findAll() throws SQLException, ClassNotFoundException {
        List<User> users = new ArrayList<>();
        Connection connection = sqlConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_QUERY);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            String username = resultSet.getString("username");
            String password = resultSet.getString("password");
            String fullname = resultSet.getString("fullname");
            String time = resultSet.getString("dateofbirth");
            LocalDate dateofbirth = LocalDate.parse(time);
            String address = resultSet.getString("address");
            String desc = resultSet.getString("desc");
            String imgLink = resultSet.getString("image");
            String role = resultSet.getString("role");
            int id = resultSet.getInt("id");
            users.add(new User(id, username, password, fullname, role, dateofbirth, address, desc, imgLink));
        }
        return users;
    }


    @Override
    public User findById(int idFind) throws SQLException, ClassNotFoundException {
        Connection connection = sqlConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(FINF_BY_ID_ADD_QUERY);
        preparedStatement.setInt(1, idFind);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            String username = resultSet.getString("username");
            String password = resultSet.getString("password");
            String fullname = resultSet.getString("fullname");

            String time = resultSet.getString("dateofbirth");
            LocalDate dateofbirth = LocalDate.parse(time);

            String address = resultSet.getString("address");
            String desc = resultSet.getString("desc");
            String image = resultSet.getString("image");
            String role = resultSet.getString("role");
            int id = resultSet.getInt("id");
            return new User(id, username, password, fullname, role, dateofbirth, address, desc, image);
        }
        return null;
    }

    @Override
    public List<User> findByName(String name) throws SQLException, ClassNotFoundException {
        List<User> userList = new ArrayList<>();
        Connection connection = sqlConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(FINF_BY_NAME_ADD_QUERY);
        preparedStatement.setString(1, "%" + name + "%");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            String username = resultSet.getString("username");
            String password = resultSet.getString("password");
            String fullname = resultSet.getString("fullname");

            String time = resultSet.getString("dateofbirth");
            LocalDate dateofbirth = LocalDate.parse(time);

            String address = resultSet.getString("address");
            String desc = resultSet.getString("desc");
            String image = resultSet.getString("image");
            String role = resultSet.getString("role");
            int id = resultSet.getInt("id");
            userList.add(new User(id, username, password, fullname, role, dateofbirth, address, desc, image));
        }
        return userList;
    }

    @Override
    public void add(User user) throws SQLException, ClassNotFoundException {
        Connection connection = sqlConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(ADD_QUERY);
        preparedStatement.setString(1, user.getUsername());
        preparedStatement.setString(2, user.getPassword());
        preparedStatement.setString(3, user.getFullName());
        preparedStatement.setString(4, user.getDateOfBirth().toString());
        preparedStatement.setString(5, user.getAddress());
        preparedStatement.setString(6, user.getDesc());
        preparedStatement.setString(7, user.getImgLink());
        preparedStatement.setString(8, user.getRole());
        preparedStatement.executeUpdate();
    }

    @Override
    public void edit(int id, User user) throws SQLException, ClassNotFoundException {
        Connection connection = sqlConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(EDIT_QUERY);
        preparedStatement.setString(1, user.getUsername());
        preparedStatement.setString(2, user.getPassword());
        preparedStatement.setString(3, user.getFullName());
        preparedStatement.setString(4, user.getDateOfBirth().toString());
        preparedStatement.setString(5, user.getAddress());
        preparedStatement.setString(6, user.getDesc());
        preparedStatement.setString(7, user.getImgLink());
        preparedStatement.setString(8, user.getRole());
        preparedStatement.setInt(9, id);
        preparedStatement.executeUpdate();
    }

    @Override
    public void delete(int id) throws SQLException, ClassNotFoundException {
        Connection connection = sqlConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY);
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();
    }

    public List<User> findAddress(String addressFind) throws SQLException, ClassNotFoundException {
        List<User> userList = new ArrayList<>();
        Connection connection = sqlConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(FINF_BY_ADDRESS_ADD_QUERY);
        preparedStatement.setString(1, "%" + addressFind + "%");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            String username = resultSet.getString("username");
            String password = resultSet.getString("password");
            String fullname = resultSet.getString("fullname");

            String time = resultSet.getString("dateofbirth");
            LocalDate dateofbirth = LocalDate.parse(time);

            String address = resultSet.getString("address");
            String desc = resultSet.getString("desc");
            String image = resultSet.getString("image");
            String role = resultSet.getString("role");
            int id = resultSet.getInt("id");
            userList.add(new User(id, username, password, fullname, role, dateofbirth, address, desc, image));
        }
        return userList;
    }
}
