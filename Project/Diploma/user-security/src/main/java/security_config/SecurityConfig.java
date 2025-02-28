package security_config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.SecurityFilterChain;


import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests( (authorizeHttpRequests) ->
                        authorizeHttpRequests
                                .requestMatchers( "/**" )
                                .permitAll()
                                //.hasRole("USER")
                                .anyRequest()
                                .authenticated()
                )
                .formLogin( withDefaults() );
        http.headers( headers -> headers
                .frameOptions( HeadersConfigurer.FrameOptionsConfig::sameOrigin ) );
        return http.build();

    }


}
