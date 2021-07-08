package dao;

import model.User;

import java.sql.SQLException;
import java.util.List;

public class UserDAO implements IDAO<User> {
    @Override
    public List<User> findAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public User findById(int id) {
        return null;
    }

    @Override
    public List<User> findByName(String name) {
        return null;
    }

    @Override
    public void add(User user) {

    }

    @Override
    public void edit(int id, User user) {

    }

    @Override
    public void delete(int id) {

    }
}
