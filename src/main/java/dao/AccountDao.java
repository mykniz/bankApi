package dao;

import entity.Account;
import java.math.BigDecimal;

public interface AccountDao extends CrudDao<Account> {

    void updateBalance(int accountId, BigDecimal value);

}
