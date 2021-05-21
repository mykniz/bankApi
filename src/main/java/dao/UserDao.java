package dao;

import dto.UserBankInfoResponseDto;
import entity.User;
import java.util.List;

public interface UserDao extends CrudDao<User> {

    List<UserBankInfoResponseDto> findUsersBankInfo();

    void saveContractor(User contractor, int userId, int contractorId);
}
