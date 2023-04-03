package com.comicreading;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.comicreading.security.ComicUserDetailsService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    
    @Autowired
    private ComicUserDetailsService userDetailsService;

    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
            .authenticationProvider(authProvider())
            .build();
    }
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // TODO this csrf disable feel icky...
        // TODO there must be a better way to import these request patterns
        http.csrf().disable()
        .authorizeHttpRequests()
                .requestMatchers("/","/login*", "/logout*", "/error*", "/js/**", "/css/**").permitAll()
                .requestMatchers("/comics","/addComic","/saveComic","/editComic","/incComic","/deleteComic","/summary", "/viewComic").hasRole("USER")
                .requestMatchers("/user/registration*","/successRegister*","/admin/logs").hasRole("ADMIN")
                // .requestMatchers("/adminOnly").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and().formLogin().defaultSuccessUrl("/summary")
                .and().logout().logoutSuccessUrl("/")
                .and().httpBasic();
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }
}
