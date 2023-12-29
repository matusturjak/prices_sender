package sk.matusturjak.price_sender.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import sk.matusturjak.price_sender.repository.impl.UserRepositoryImpl;

@AllArgsConstructor
@Service
public class UserService {

    private final UserRepositoryImpl userRepository;
}
