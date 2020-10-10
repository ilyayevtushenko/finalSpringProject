package com.example.finalSpringProject.config;

import com.example.finalSpringProject.model.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@AllArgsConstructor(onConstructor = @__(@Autowired))
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private UserService userService;

    private PasswordEncoder passwordEncoder;

    private LoginSuccessHandler loginSuccessHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "/index", "/registration", "/signUp", "/login").permitAll()
                .antMatchers("/admin", "/admin/**", "/cancel", "/cancel/**").hasAnyAuthority("ADMIN")
                .antMatchers("/user", "user/**").hasAnyAuthority("USER")

                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).permitAll()
                .and().exceptionHandling()
                .accessDeniedPage("/error")
                .and()
                .csrf().disable().formLogin().failureUrl("/login?loginError=true").loginPage("/login").successHandler(loginSuccessHandler).permitAll()
                .usernameParameter("email")
                .passwordParameter("password");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService)
                .passwordEncoder(passwordEncoder);

    }

    @Override
    public void configure(WebSecurity web) {
        web
                .ignoring()
                .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
    }
}
