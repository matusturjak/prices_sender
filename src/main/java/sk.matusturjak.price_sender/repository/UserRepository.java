package sk.matusturjak.price_sender.repository;

import sk.matusturjak.price_sender.model.db.User;

import java.util.List;

public interface UserRepository {
    List<User> findAll();
}
