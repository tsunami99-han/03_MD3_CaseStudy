package dao;

import java.sql.SQLException;
import java.util.List;

public interface IDAO<T> {
    List<T> findAll() throws SQLException, ClassNotFoundException;

    T findById(int id);

    List<T> findByName(String name);

    void add(T t);

    void edit(int id, T t);

    void delete(int id);
}
