package exception;

import java.sql.SQLException;

public class AccountDaoException extends SQLException {
    public AccountDaoException(String reason) {
        super(reason);
    }
}
