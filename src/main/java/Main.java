import config.DatabaseConfig;
import config.ServerConfig;
import dao.*;
import service.AccountService;
import service.CardService;
import service.UserService;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws IOException, SQLException {

        Connection connection = DatabaseConfig.getConnection();
        DatabaseConfig.createDatabase(connection);
        UserDao userDao = new UserDaoImpl(connection);
        AccountDao accountDao = new AccountDaoImpl(connection);
        CardDao cardDao = new CardDaoImpl(connection);

        UserService userService = new UserService(userDao);
        AccountService accountService = new AccountService(accountDao);
        CardService cardService = new CardService(cardDao);

        ServerConfig.startServer(userService,cardService,accountService);

    }
}
