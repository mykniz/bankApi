package service;

import dao.UserDao;
import dto.AddContractorRequestDto;
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
    public List<UserBankInfoResponseDto> findUsersBankInfo() {
        return userDao.findUsersBankInfo();
    }

    public void addContractor(AddContractorRequestDto addContractorRequestDto) {

        int userId = addContractorRequestDto.getUserId();
        int contractorId = addContractorRequestDto.getContractorId();

        if(userId != contractorId) {
         User contractor = userDao.findById(contractorId).orElseThrow(() -> new RuntimeException("user not find"));
         userDao.saveContractor(contractor, userId ,contractorId);
        }
    }
}
