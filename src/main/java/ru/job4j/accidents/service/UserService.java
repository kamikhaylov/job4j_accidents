package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.User;
import ru.job4j.accidents.repository.data.AuthorityRepository;
import ru.job4j.accidents.repository.data.UserRepository;

import java.util.Optional;

import static ru.job4j.accidents.common.logging.AccidentLogEvent.ACC0006;

/**
 * Сервисный слой для работы с пользователями
 */
@ThreadSafe
@Service
@AllArgsConstructor
public class UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class.getName());

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final AuthorityRepository authorities;

    public Optional<User> create(User user) {
        user.setEnabled(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setAuthority(authorities.findByAuthority("ROLE_USER"));
        Optional<User> result = Optional.empty();
        try {
            result = Optional.of(userRepository.save(user));
        } catch (Exception exception) {
            LOGGER.error(ACC0006.toString() + " " + exception.getMessage(), exception);
        }
        return result;
    }
}
