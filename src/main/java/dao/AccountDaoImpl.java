package dao;

import config.DatabaseConfig;
import entity.Account;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AccountDaoImpl implements AccountDao{

    private final static String SQL_FIND_ALL = "SELECT * FROM ACCOUNT";

    @Override
    public Optional<Account> findById(int id) {
        return Optional.empty();
    }

    @Override
    public void save(Account model) {

    }

    @Override
    public void update(Account model) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<Account> findAll() throws SQLException, FileNotFoundException {
        Connection connection = DatabaseConfig.getConnection();
        List<Account> accountList = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SQL_FIND_ALL);
            while (resultSet.next()) {
                accountList.add(new Account(
                        resultSet.getString(2),
                        resultSet.getBigDecimal(3),
                        resultSet.getBoolean(4)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        connection.close();
        return accountList;
    }
}
