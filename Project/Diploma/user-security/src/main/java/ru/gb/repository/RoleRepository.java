//package ru.gb.repository;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import ru.gb.api.Role;
//import ru.gb.api.RoleEnum;
//
//import java.util.Optional;
//
//public interface RoleRepository extends JpaRepository<Role, Long> {
//    Optional<Role> findByName(RoleEnum name);
//
//    boolean existsByName(RoleEnum name);
//}
