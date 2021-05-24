package controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import config.DatabaseConfig;
import config.ServerConfig;
import converter.HttpExchangeToMapParser;
import converter.JsonResponseParser;
import dao.*;
import dto.AddContractorRequestDto;
import entity.Client;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.AccountService;
import service.CardService;
import service.ClientService;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class ClientControllerTest {

    @BeforeEach
    void setUp() throws IOException {
        DatabaseConfig.createTables();
        ClientDao clientDao = new ClientDaoImpl();
        AccountDao accountDao = new AccountDaoImpl();
        CardDao cardDao = new CardDaoImpl();
        ClientService clientService = new ClientService(clientDao);
        AccountService accountService = new AccountService(accountDao, clientDao);
        CardService cardService = new CardService(cardDao);
        ServerConfig.startServer(clientService, cardService, accountService);
    }

    @Test
    void handle() throws IOException {
        String url = "http://localhost:8000/client/addContractor";
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestProperty("Content-Type”", "application/json");
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        connection.connect();

        String jsonString = "{\"clientId\":\"1\",\"contractorId\":\"2\"}";
        ObjectMapper mapper = new ObjectMapper();
        JsonNode requestJson = mapper.readTree(jsonString);

        try (OutputStreamWriter osw = new OutputStreamWriter(connection.getOutputStream(), StandardCharsets.UTF_8)) {
            osw.write(String.valueOf(requestJson.toString()));
        }

        StringBuilder sb = new StringBuilder();
        try( BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
            String line = null;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
        }

        JsonNode responseJson = mapper.readTree(String.valueOf(sb));

        String testResponseString = "[\n" +
                "  {\n" +
                "    \"firstName\": \"Leo\",\n" +
                "    \"lastName\": \"Messi\",\n" +
                "    \"phoneNumber\": \"+7(070)-246-3274\",\n" +
                "    \"contractorsList\": [\n" +
                "      {\n" +
                "        \"firstName\": \"John\",\n" +
                "        \"lastName\": \"Smith\",\n" +
                "        \"phoneNumber\": \"+7(712)-305-9426\",\n" +
                "        \"clientId\": 1,\n" +
                "        \"contractorId\": 2\n" +
                "      }\n" +
                "    ],\n" +
                "    \"accountList\": [\n" +
                "      {\n" +
                "        \"account\": \"64419815581464276017\",\n" +
                "        \"balance\": 1000000.00,\n" +
                "        \"clientId\": 1,\n" +
                "        \"cardList\": [\n" +
                "          {\n" +
                "            \"cardNumber\": \"4994125505622193\",\n" +
                "            \"cardType\": \"CREDIT\",\n" +
                "            \"paySystem\": \"VISA\",\n" +
                "            \"accountId\": 1,\n" +
                "            \"active\": true\n" +
                "          },\n" +
                "          {\n" +
                "            \"cardNumber\": \"9495700472568181\",\n" +
                "            \"cardType\": \"CREDIT\",\n" +
                "            \"paySystem\": \"VISA\",\n" +
                "            \"accountId\": 1,\n" +
                "            \"active\": true\n" +
                "          }\n" +
                "        ],\n" +
                "        \"open\": true\n" +
                "      },\n" +
                "      {\n" +
                "        \"account\": \"66546345908858348082\",\n" +
                "        \"balance\": 9999999999.00,\n" +
                "        \"clientId\": 1,\n" +
                "        \"cardList\": [\n" +
                "          {\n" +
                "            \"cardNumber\": \"1032274611722232\",\n" +
                "            \"cardType\": \"CREDIT\",\n" +
                "            \"paySystem\": \"MASTERCARD\",\n" +
                "            \"accountId\": 2,\n" +
                "            \"active\": true\n" +
                "          }\n" +
                "        ],\n" +
                "        \"open\": true\n" +
                "      }\n" +
                "    ]\n" +
                "  },\n" +
                "  {\n" +
                "    \"firstName\": \"John\",\n" +
                "    \"lastName\": \"Smith\",\n" +
                "    \"phoneNumber\": \"+7(712)-305-9426\",\n" +
                "    \"contractorsList\": [],\n" +
                "    \"accountList\": [\n" +
                "      {\n" +
                "        \"account\": \"03217462696778617732\",\n" +
                "        \"balance\": 10.00,\n" +
                "        \"clientId\": 2,\n" +
                "        \"cardList\": [\n" +
                "          {\n" +
                "            \"cardNumber\": \"1772841203002047\",\n" +
                "            \"cardType\": \"DEBET\",\n" +
                "            \"paySystem\": \"MIR\",\n" +
                "            \"accountId\": 3,\n" +
                "            \"active\": true\n" +
                "          }\n" +
                "        ],\n" +
                "        \"open\": true\n" +
                "      }\n" +
                "    ]\n" +
                "  }\n" +
                "]";
        JsonNode testJson = mapper.readTree(testResponseString);
        Assertions.assertEquals(testJson, responseJson);
    }
}