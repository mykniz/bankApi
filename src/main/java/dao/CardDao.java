package dao;

import entity.Card;

import java.util.List;
import java.util.Optional;

public interface CardDao extends CrudDao<Card> {

    @Override
    Optional<Card> findById(int id);

    List<Card> findCardsByAccountId(int accountId);
}
