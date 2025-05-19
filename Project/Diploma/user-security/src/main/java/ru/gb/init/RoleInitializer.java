package ru.gb.init;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.gb.api.Role;
import ru.gb.api.RoleEnum;
import ru.gb.repository.RoleRepository;

import java.util.Arrays;

@Component
public class RoleInitializer implements CommandLineRunner {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    @Transactional
    public void run(String... args) {
        Arrays.stream( RoleEnum.values()).forEach( role -> {
            if (!roleRepository.existsByName(role)) {
                Role newRole = new Role();
                newRole.setName(role);
                roleRepository.save(newRole);
            }
        });
    }
}
