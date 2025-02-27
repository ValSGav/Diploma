package api;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "auth_users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
}
