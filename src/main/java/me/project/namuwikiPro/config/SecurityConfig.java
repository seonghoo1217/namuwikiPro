package me.project.namuwikiPro.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import me.project.namuwikiPro.service.Memberservice;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;


@RequiredArgsConstructor
@EnableWebSecurity // 1
@Configuration
@Log4j2
public class SecurityConfig extends WebSecurityConfigurerAdapter { // 2

    private final Memberservice memberservice;
    private final AuthenticationFailureHandler customFailurHandler;

    @Bean
    PasswordEncoder passwordEncoder(){
       return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web)throws Exception{
        web.ignoring().antMatchers("/css/**","/js/**","/lib/**","/img/**");
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                    .antMatchers("/","/loginuser/login","loginuser/register").permitAll()
                    .antMatchers("/loginuser/member","/board/**").hasRole("USER")
                    .antMatchers("/loginuser/admin").hasRole("ADMIN")
                    .and()
                .formLogin()
                    .loginPage("/loginuser/login")
                    .usernameParameter("username")
                    .passwordParameter("password")
                    .loginProcessingUrl("/login_proc")
                    .defaultSuccessUrl("/")
                    .failureHandler(customFailurHandler)
                    .permitAll()
                    .and()
                .logout()
                    .logoutUrl("/doLogout")
                    .logoutSuccessUrl("/")
                    .invalidateHttpSession(true)
                    .and()
                .exceptionHandling();


    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user1")
                .password("$2a$10$6ATJ4VRAzm3Ou7M8xH/iOeO9FbMSsGplquVo0WypdU.EOmCgHlFhi")
                .roles("USER","ALLOW")
                .and()
                .withUser("seonghoo1217")
                .password("$2a$10$gOXZ7vxyaJC8fH9pdLiU8e5QpV2zfOcT6n0LzUfa4ckMclxJiB01.")
                .roles("USER","ALLOW","ADMIN");

        auth.userDetailsService(memberservice).passwordEncoder(passwordEncoder());

    }



}