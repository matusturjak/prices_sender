package sk.matusturjak.price_sender.service;

import org.springframework.stereotype.Service;
import sk.matusturjak.price_sender.repository.impl.UserRepositoryImpl;

@Service
public class UserService {

    private final UserRepositoryImpl userRepository;

    public UserService(UserRepositoryImpl userRepository) {
        this.userRepository = userRepository;
    }
}
