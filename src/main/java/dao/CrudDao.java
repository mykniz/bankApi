package dao;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface CrudDao<T> {

    Optional<T> findById(int id);
    void save(T model) throws SQLException, FileNotFoundException;
    void update(T model);
    void delete(Long id);

    List<T> findAll() throws SQLException, FileNotFoundException;
}
