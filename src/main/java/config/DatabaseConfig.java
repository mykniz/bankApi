package config;

import org.h2.tools.RunScript;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * Database configuration
 */
public class DatabaseConfig {
    public static final String ANSI_CYAN = "\u001B[36m";

    private final static String PROPERTIES_PATH = "src/main/resources/db.properties";
    private final static String SQL_CLIENT_SCRIPT_PATH = "src/main/resources/scripts/client.sql";
    private final static String SQL_ACCOUNT_SCRIPT_PATH = "src/main/resources/scripts/account.sql";
    private final static String SQL_CARD_SCRIPT_PATH = "src/main/resources/scripts/card.sql";
    private final static String SQL_CONTRACTOR_SCRIPT_PATH = "src/main/resources/scripts/contractor.sql";
    private final static String SQL_TRANSACTION_SCRIPT_PATH = "src/main/resources/scripts/transaction.sql";

    private static final Logger log = Logger.getLogger(DatabaseConfig.class.getName());

    /**
     * @return Database Connection
     */
    public static Connection getConnection() {
        Connection connection;
        try (InputStream inputStream = new FileInputStream(PROPERTIES_PATH)) {
            Properties properties = new Properties();
            properties.load(inputStream);

            String dbUrl = properties.getProperty("db.url");
            String dbUsername = properties.getProperty("db.username");
            String dbPassword = properties.getProperty("db.password");
            String dbDriverClassName = properties.getProperty("db.driverClassName");

            Class.forName(dbDriverClassName);
            connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);

        } catch (IOException | SQLException | ClassNotFoundException e) {
            throw new IllegalStateException();
        }
        log.info(ANSI_CYAN + "connection to DB established" + ANSI_CYAN);
        return connection;
    }

    /**
     * create tables USER, ACCOUNT, CARD, CONTRACTOR
     */
    public static void createTables() {
        try (Connection connection = DatabaseConfig.getConnection()) {
            RunScript.execute(connection, new FileReader(SQL_CLIENT_SCRIPT_PATH));
            RunScript.execute(connection, new FileReader(SQL_CONTRACTOR_SCRIPT_PATH));
            RunScript.execute(connection, new FileReader(SQL_ACCOUNT_SCRIPT_PATH));
            RunScript.execute(connection, new FileReader(SQL_CARD_SCRIPT_PATH));
            RunScript.execute(connection, new FileReader(SQL_TRANSACTION_SCRIPT_PATH));
        } catch (SQLException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
