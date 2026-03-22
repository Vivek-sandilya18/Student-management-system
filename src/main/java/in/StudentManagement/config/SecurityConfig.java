package in.StudentManagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            // CSRF disable (forms easily kaam kare)
            .csrf(csrf -> csrf.disable())

            // Abhi sab pages open rakho (testing mode)
            .authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll()
            )

            // Form login completely disable
            .formLogin(form -> form.disable())

            // Logout bhi disable
            .logout(logout -> logout.disable());

        return http.build();
    }
}
