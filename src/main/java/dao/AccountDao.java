package dao;

import dto.TopUpRequestDto;
import entity.Account;

public interface AccountDao extends CrudDao<Account> {

    void updateBalance(TopUpRequestDto topUpRequestDto);

}
