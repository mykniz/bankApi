import config.DatabaseConfig;
import config.ServerConfig;
import dao.*;
import service.AccountService;
import service.CardService;
import service.UserService;

import java.io.IOException;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws IOException, SQLException {

        DatabaseConfig.createTables();

        UserDao userDao = new UserDaoImpl();
        AccountDao accountDao = new AccountDaoImpl();
        CardDao cardDao = new CardDaoImpl();

        UserService userService = new UserService(userDao);
        AccountService accountService = new AccountService(accountDao, userDao);
        CardService cardService = new CardService(cardDao);

        ServerConfig.startServer(userService,cardService,accountService);
    }
}
