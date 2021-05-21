package dao;

import dto.UserBankInfoResponseDto;
import entity.User;
import java.util.List;

public interface UserDao extends CrudDao<User> {

    @Override
    List<User> findAll();

    List<UserBankInfoResponseDto> findUsersBankInfo();

    void saveContractor(User contractor, int userId, int contractorId);
}
