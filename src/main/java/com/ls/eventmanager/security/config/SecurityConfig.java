package com.ls.eventmanager.security.config;

import com.ls.eventmanager.security.jwt.JwtAuthEntryPoint;
import com.ls.eventmanager.security.jwt.JwtAuthTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.sql.DataSource;
import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    DataSource dataSource;

    @Autowired
    private JwtAuthEntryPoint unauthorizedHandler;

    @Bean
    public JwtAuthTokenFilter authenticationJwtTokenFilter() {
        return new JwtAuthTokenFilter();
    }


    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.cors(httpSecurityCorsConfigurer ->
                httpSecurityCorsConfigurer.configurationSource(request ->
                        new CorsConfiguration().applyPermitDefaultValues()
                )
        );
        http.csrf(csrf-> csrf.disable());

        http.authorizeHttpRequests(authorizeRequests ->
                authorizeRequests.requestMatchers("/h2-console/**").permitAll()
                        .requestMatchers("/public/**").permitAll()
                        .requestMatchers(HttpMethod.GET,"/api/event/**").permitAll()
                        .requestMatchers(HttpMethod.GET,"/api/user/**").permitAll()

                        .requestMatchers("/api/auth/**").permitAll()
                        .anyRequest().authenticated());
        http.sessionManagement(
                session ->
                        session.sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS)
        );
        http.exceptionHandling(exception -> exception.authenticationEntryPoint(unauthorizedHandler));
        //http.httpBasic(withDefaults());
        http.headers(headers -> headers
                .frameOptions(frameOptions -> frameOptions
                        .sameOrigin()
                )
        );


        http.addFilterBefore(authenticationJwtTokenFilter(),
                UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {
        return builder.getAuthenticationManager();
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public UserDetailsService userDetailsService(DataSource dataSource) {
//        return new JdbcUserDetailsManager(dataSource);
//    }

//    @Bean
//    public CommandLineRunner initData(UserDetailsService userDetailsService) {
//        return args -> {
//            JdbcUserDetailsManager manager = (JdbcUserDetailsManager) userDetailsService;
//            UserDetails user1 = User.withUsername("user1")
//                    .password(passwordEncoder().encode("1234"))
//                    .roles("USER")
//                    .build();
//            UserDetails admin = User.withUsername("admin")
//                    //.password(passwordEncoder().encode("adminPass"))
//                    .password(passwordEncoder().encode("1234"))
//                    .roles("ADMIN")
//                    .build();
//
//            JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager(dataSource);
//            userDetailsManager.createUser(user1);
//            userDetailsManager.createUser(admin);
//        };
//    }

}
