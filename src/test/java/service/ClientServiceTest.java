package service;

import config.DatabaseConfig;
import dao.ClientDao;
import dao.ClientDaoImpl;
import dto.AddContractorRequestDto;
import dto.ClientRequestDto;
import entity.Account;
import entity.Client;
import entity.Contractor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ClientServiceTest {

    @BeforeEach
    void setUp() {
        DatabaseConfig.createTables();
    }

    @Test
    void findAll() {
        Assertions.assertEquals(4, new ClientService(new ClientDaoImpl()).findAll().size());
    }

    @Test
    void findClientsBankInfo() {
        ClientDao clientDao = new ClientDaoImpl();
        ClientService clientService = new ClientService(clientDao);
        Assertions.assertNotNull(clientService.findClientsBankInfo());
    }

    @Test
    void addContractor() {
        ClientDao clientDao = new ClientDaoImpl();
        ClientService clientService = new ClientService(clientDao);
        AddContractorRequestDto addContractorRequestDto = new AddContractorRequestDto(1,2);
        clientService.addContractor(addContractorRequestDto);
        Client cLient = clientService.findAll().get(1);
        List<Contractor> contractorList = cLient.getContractorsList();
        Assertions.assertNotNull(contractorList);
    }

    @Test
    void addClient() {
        ClientDao clientDao = new ClientDaoImpl();
        ClientService clientService = new ClientService(clientDao);

        int originSize = clientService.findAll().size();
        ClientRequestDto clientRequestDto = new ClientRequestDto("testFirstName",
                "testLastName",
                "testPhoneNumber");
        clientService.addClient(clientRequestDto);
        int newSize = clientService.findAll().size();
        Assertions.assertEquals(originSize + 1, newSize);
    }
}