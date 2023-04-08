package com.example.demo.loggin;

import com.example.demo.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    private final UserRepository userRepository;

    public DataLoader(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String password = bCryptPasswordEncoder.encode("password");
        String password2 = bCryptPasswordEncoder.encode("password2");
        userRepository.save(new User("Fabrizio", "fabrim", "fabrimeloni1@gmail.com", password, AppUserRoles.USER));
        userRepository.save(new User("Daniel", "danii", "dani@gmail.com", password2, AppUserRoles.ADMIN));

    }
}
