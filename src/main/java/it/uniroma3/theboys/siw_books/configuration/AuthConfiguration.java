package it.uniroma3.theboys.siw_books.configuration;

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
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static it.uniroma3.theboys.siw_books.model.Credenziali.ADMIN_ROLE;
import static it.uniroma3.theboys.siw_books.model.Credenziali.UTENTE_ROLE;

//import static org.springframework.security.config.Customizer.withDefaults;

import javax.sql.DataSource;

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
                .authoritiesByUsernameQuery("SELECT username, role from credenziali WHERE username=?")
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
                // Pagine pubbliche e risorse statiche
                .requestMatchers(HttpMethod.GET, "/", "/index", "/login", "/areaRiservata/**", "/landingPage/**", "/favicon.ico").permitAll()
                // Registrazione e login aperti a tutti (POST)
                .requestMatchers(HttpMethod.GET, "/autore/registrazione/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/giocatore/registrazione/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/autore/registrazione").permitAll()
                .requestMatchers(HttpMethod.POST, "/giocatore/registrazione").permitAll()
                // Area amministrativa solo per autore
                .requestMatchers("/autore/**").hasAuthority(ADMIN_ROLE)
                // Endpoint di eliminazione raccolta
                .requestMatchers(HttpMethod.POST, "/autore/prova").hasAuthority(ADMIN_ROLE)
                // Area amministrativa solo per giocatore
                .requestMatchers(HttpMethod.POST, "/autore/**").hasAuthority(ADMIN_ROLE)
                .requestMatchers(HttpMethod.POST, "/giocatore/**").hasAuthority(UTENTE_ROLE)
                // Tutte le altre richieste devono essere autenticate
                .anyRequest().authenticated()
            )
            .formLogin(login -> login
                .loginPage("/login")
                .permitAll()
                .defaultSuccessUrl("/success", true)
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