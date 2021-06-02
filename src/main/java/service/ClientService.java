package service;

import dao.ClientDao;
import dto.AddContractorRequestDto;
import dto.ClientBankInfoResponseDto;
import dto.ClientRequestDto;
import entity.Account;
import entity.Client;
import entity.Contractor;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class ClientService {
    private static final Logger log = Logger.getLogger(ClientService.class.getName());
    private final ClientDao clientDao;

    public ClientService(ClientDao clientDao) {
        this.clientDao = clientDao;
    }

    public List<Client> findAll() {
        return clientDao.findAll();
    }
    public List<ClientBankInfoResponseDto> findClientsBankInfo() {
        return clientDao.findClientsBankInfo();
    }

    public void addContractor(AddContractorRequestDto addContractorRequestDto) {

        int clientId = addContractorRequestDto.getClientId();
        int contractorId = addContractorRequestDto.getContractorId();

        if(clientId != contractorId) { //todo
         Client contractorForClient = clientDao.findById(contractorId).orElseThrow(() -> new RuntimeException("client not found"));
         Contractor contractor = new Contractor(contractorForClient.getFirstName(), contractorForClient.getLastName(),contractorForClient.getPhoneNumber(),clientId,contractorId);
         clientDao.saveContractor(contractor);
        }
    }

    public void addClient(ClientRequestDto clientRequestDto) {
        String phoneNumber = clientRequestDto.getPhoneNumber();

        if(clientDao.findByPhoneNumber(phoneNumber).isPresent()) {
            log.info("client already exists");
        } else {
            String firstName = clientRequestDto.getFirstName();
            String lastName = clientRequestDto.getLastName();
            List<Contractor> contractorList = new ArrayList<>();
            List<Account> accountList = new ArrayList<>();
            Client client = new Client(firstName, lastName, phoneNumber, contractorList, accountList);
            clientDao.save(client);
        }
    }
}
