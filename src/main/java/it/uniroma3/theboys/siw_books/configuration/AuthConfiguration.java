package it.uniroma3.theboys.siw_books.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


import static it.uniroma3.theboys.siw_books.model.Credenziali.ADMIN_ROLE;
import static it.uniroma3.theboys.siw_books.model.Credenziali.UTENTE_ROLE;
;


@Configuration
@EnableWebSecurity
public class AuthConfiguration {

    @Autowired
    private DataSource dataSource;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .authoritiesByUsernameQuery("SELECT username, ruolo from credenziali WHERE username=?")
                .usersByUsernameQuery("SELECT username, password, 1 as enabled FROM credenziali WHERE username=?");
    }
    
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    protected SecurityFilterChain configure(final HttpSecurity httpSecurity) throws Exception {
        httpSecurity
            .cors(cors -> cors.disable())
            .authorizeHttpRequests(requests -> requests
                .requestMatchers(HttpMethod.GET, "/index", "/libro/**", "/autore/**", "/autori/**", "/registrazione/**", "/favicon.ico", "/css/style.css", "/uploads/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/eliminazioneRecensione/**", "/modificaAutore/**", "/eliminazioneAutore/**", "/modificaLibro/**", "eliminazioneLibro/**", "/autoreForm/**", "/libroForm/**").hasAuthority(ADMIN_ROLE)
                .requestMatchers(HttpMethod.POST, "/aggiuntaAutore/**", "/aggiuntaLibro/**", "/modificaAutore/**", "/modificaLibro/" ).hasAuthority(ADMIN_ROLE)
                .requestMatchers(HttpMethod.POST, "/aggiuntaRecensione/**").hasAuthority(UTENTE_ROLE)
                .anyRequest().authenticated()
            )
            .formLogin(login -> login
                .loginPage("/login")
                .permitAll()
                .defaultSuccessUrl("/index", true)
                .failureUrl("/login?error=true")
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .clearAuthentication(true)
                .permitAll()
            );

        return httpSecurity.build();
    }

}