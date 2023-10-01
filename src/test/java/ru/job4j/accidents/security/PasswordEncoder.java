package ru.job4j.accidents.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoder {

    private static final Logger LOGGER = LoggerFactory.getLogger(PasswordEncoder.class.getName());

    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String pwd = encoder.encode("1");
        LOGGER.info(pwd);
    }
}
