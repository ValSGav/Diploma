import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "user-security")
@ComponentScan(basePackages = "user-security")
public class demo {
    public static void main(String[] args) {
        SpringApplication.run( demo.class, args );

    }

}
