//package ru.gb.init;
//
//import jakarta.transaction.Transactional;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//import ru.gb.api.Role;
//import ru.gb.api.RoleEnum;
//import ru.gb.repository.RoleRepository;
//import ru.gb.repository.UserRepository;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Component
//public class RoleInitializer implements CommandLineRunner {
//    @Autowired
//    private RoleRepository roleRepository;
//    @Autowired
//    private UserRepository UserRepository;
//
//    @Override
//    @Transactional
//    public void run(String... args) throws Exception {
//
//        List<Role> roles = Arrays.stream( RoleEnum.values() )
//                .map( roleEnum -> {
//                    Role role = new Role();
//                    role.setName( roleEnum );
//                    return role;
//                } )
//                .collect( Collectors.toList() );
//
//        roles.forEach( role -> {
//            if ( !roleRepository.existsByName( role.getName() ) ) {
//                roleRepository.save( role );
//            }
//        } );
//
//    }
//}
