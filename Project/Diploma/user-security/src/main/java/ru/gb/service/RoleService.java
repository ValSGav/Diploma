//package ru.gb.service;
//
//import jakarta.annotation.PostConstruct;
//import jakarta.transaction.Transactional;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import ru.gb.api.Role;
//import ru.gb.api.RoleEnum;
//import ru.gb.api.User;
//import ru.gb.repository.RoleRepository;
//
//import javax.management.relation.RoleNotFoundException;
//import java.util.List;
//
//@Service
//@Transactional
//public class RoleService {
//
//    @Autowired
//    private RoleRepository roleRepository;
//
//    public Role createRole(Role role) {
//        return roleRepository.save( role );
//    }
//
//    public void assignRoleToUser(User user, RoleEnum roleEnum) throws RoleNotFoundException {
//        Role role = roleRepository.findByName( roleEnum)
//                .orElseThrow( () -> new RoleNotFoundException( "Role not found: " + roleEnum.name() ) );
//        user.getRoles().add( role );
//    }
//
//    public Role getRoleByName(RoleEnum roleEnum) throws RoleNotFoundException {
//        return roleRepository.findByName( roleEnum )
//                .orElseThrow( () -> new RoleNotFoundException( "Role not found: " + roleEnum.name() ) );
//    }
//
//    public List<Role> getAllRoles() {
//        return roleRepository.findAll();
//    }
//
//    @PostConstruct
//    private void createRoles(){
//        Role roleUser;
//        //roleRepository.save(  )
//    }
//}
