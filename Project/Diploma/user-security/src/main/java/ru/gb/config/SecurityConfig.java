package ru.gb.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
//@EnableGlobalAuthentication()
public class SecurityConfig {
//
  @Autowired
    private UserDetailsService userDetailsService;
//
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize ->
                        authorize
                                .requestMatchers("api/users/register/").permitAll()
                                //.requestMatchers("api/users/login").permitAll()
                                .anyRequest().permitAll()
                                //.requestMatchers("api/users/photographer/**").hasRole("PHOTOGRAPHER")
                               // .requestMatchers("api/users/client/**").hasRole("CLIENT")
                                //.anyRequest().authenticated()
                )  .csrf(csrf -> csrf.disable())
                .httpBasic( Customizer.withDefaults());;
//                .formLogin(httpSecurityFormLoginConfigurer ->
//                        httpSecurityFormLoginConfigurer
//                                //.loginPage("/login")
//                                .permitAll()
//
//                )
        //.userDetailsService( userDetailsService );
        return http.build();
    }



//}

}
