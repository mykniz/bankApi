package config;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

public class DatabaseConfigTest {


    public static Connection init() {
        return DatabaseConfig.getConnection();
    }

    @Test
    void getConnection() {
        Assertions.assertNotNull(DatabaseConfigTest.init());
    }



}