package service;

import dao.UserDao;
import dto.UserBankInfoResponseDto;
import entity.User;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.List;

public class UserService {
    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public List<User> findAll() throws SQLException, FileNotFoundException {
        return userDao.findAll();
    }
    public List<UserBankInfoResponseDto> findUsersWithCards() {
        return userDao.findUsersBankInfo();
    }
}
