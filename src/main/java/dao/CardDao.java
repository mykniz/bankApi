package dao;

import entity.Card;

import java.util.Optional;

public interface CardDao extends CrudDao<Card> {

    @Override
    Optional<Card> findById(int id);
}
