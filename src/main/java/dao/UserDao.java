package dao;

import dto.UserBankInfoResponseDto;
import entity.User;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.List;

public interface UserDao extends CrudDao<User> {

    @Override
    List<User> findAll() throws SQLException, FileNotFoundException;

    List<UserBankInfoResponseDto> findUsersBankInfo();
}
