package dao;

import java.sql.SQLException;
import java.util.List;

public interface IDAO<T> {
    List<T> findAll() throws SQLException, ClassNotFoundException;

    T findById(int id) throws SQLException, ClassNotFoundException;

    List<T> findByName(String name);

    void add(T t) throws SQLException, ClassNotFoundException;

    void edit(int id, T t) throws SQLException, ClassNotFoundException;

    void delete(int id);
}
