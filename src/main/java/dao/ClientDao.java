package dao;

import dto.ClientBankInfoResponseDto;
import entity.Client;

import java.util.List;
import java.util.Optional;

public interface ClientDao extends CrudDao<Client> {

    List<ClientBankInfoResponseDto> findClientsBankInfo();

    Optional<Client> findByPhoneNumber(String phoneNumber);

    void saveContractor(Client contractor, int userId, int contractorId);
}
