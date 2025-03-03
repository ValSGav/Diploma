package security_config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests( (authorizeHttpRequests) ->
                        authorizeHttpRequests
                                .requestMatchers("/register" )
                                .not()
                                .fullyAuthenticated()
                                .requestMatchers( "/login" )
                                .permitAll()
                                //.hasRole("USER")
                                .anyRequest()
                                .authenticated()
                )
                .formLogin( httpSecurityFormLoginConfigurer -> httpSecurityFormLoginConfigurer
                        .loginPage( "login.html" ) );
        http.headers( headers -> headers
                .frameOptions( HeadersConfigurer.FrameOptionsConfig::sameOrigin ) );
        return http.build();

    }


}
