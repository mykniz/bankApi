package dao;

import dto.CardDto;
import entity.Card;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.Optional;

public interface CardDao extends CrudDao<Card> {

    @Override
    Optional<Card> findById(int id);
}
