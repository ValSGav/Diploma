import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication(scanBasePackages = "ru.gb.*")
@EnableJpaRepositories(basePackages = "ru.gb.repository")
@ComponentScan(basePackages = "ru.gb.*")
@EntityScan(basePackages = "ru.gb.api")

public class api {
    public static void main(String[] args) {
        SpringApplication.run( api.class, args );

    }

}
