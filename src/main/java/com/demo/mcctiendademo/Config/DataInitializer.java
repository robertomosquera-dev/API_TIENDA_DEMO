package com.demo.mcctiendademo.Config;

import com.demo.mcctiendademo.Repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final IUserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {

    }

}
