import config.DatabaseConfig;
import config.ServerConfig;
import dao.*;
import service.AccountService;
import service.CardService;
import service.ClientService;

import java.io.IOException;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws IOException, SQLException {

        DatabaseConfig.createTables();

        ClientDao clientDao = new ClientDaoImpl();
        AccountDao accountDao = new AccountDaoImpl();
        CardDao cardDao = new CardDaoImpl();

        ClientService clientService = new ClientService(clientDao);
        AccountService accountService = new AccountService(accountDao, clientDao);
        CardService cardService = new CardService(cardDao);

        ServerConfig.startServer(clientService,cardService,accountService);
    }
}
