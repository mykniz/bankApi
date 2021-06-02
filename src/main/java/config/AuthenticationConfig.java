package config;

import com.sun.net.httpserver.BasicAuthenticator;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Properties;

public class AuthenticationConfig extends BasicAuthenticator {

    private final static String CREDENTIALS_PATH = "src/main/resources/credentials.properties"; //todo

    /**
     * Creates a BasicAuthenticator for the given HTTP realm
     *
     * @param realm The HTTP Basic authentication realm
     * @throws NullPointerException if the realm is an empty string
     */
    public AuthenticationConfig(String realm) {
        super(realm);
    }

    @Override
    public boolean checkCredentials(String username, String password) {
        boolean check = false;
        try (InputStream inputStream = new FileInputStream(CREDENTIALS_PATH)) {
            Properties properties = new Properties();
            properties.load(inputStream);
            String usernameOrigin = properties.getProperty("username");
            String passwordOrigin = new String(Base64.getEncoder().encode(properties.getProperty("password").getBytes(StandardCharsets.UTF_8)));
            password = new String(Base64.getEncoder().encode(password.getBytes(StandardCharsets.UTF_8)));
            check = username.equals(usernameOrigin) && password.equals(passwordOrigin);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return check;
    }
}
