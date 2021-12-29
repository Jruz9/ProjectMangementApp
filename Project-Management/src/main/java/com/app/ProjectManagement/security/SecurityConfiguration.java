package com.app.ProjectManagement.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    // spring is smart enough to detect if datasource is h2 or a database.
    @Autowired
    DataSource dataSource;

    //uses the web config in our file to use bcrypto as the bean and autowires
    @Autowired
    BCryptPasswordEncoder bCryptEncoder;


    //Configuration class builds the rules for the the spring login
    @Override
    protected void configure(AuthenticationManagerBuilder auth)throws Exception{
        auth.jdbcAuthentication()
                .usersByUsernameQuery("select username, password, enabled"+
                        "from user_accounts where username= ?")
                .authoritiesByUsernameQuery("select username, role "+
                        "from user_accounts where username = ?")
                .dataSource(dataSource)
                .passwordEncoder(bCryptEncoder);//decoded the password

    }
    protected void  configure(HttpSecurity http) throws Exception{
        //rules are top to down admins are first then everyone else
        http.authorizeRequests()
                .antMatchers("/projects/new").hasRole("Admin")
                .antMatchers("/projects/save").hasRole("Admin")
                .antMatchers("/employee/new").hasRole("Admin")
                .antMatchers("/employee/save").hasRole("Admin")
                .antMatchers("/","/**").permitAll()
                .and()
                .formLogin();//defacult login form

        //.loginPage("/login-page") used for custom login vice versa for logout
    }

}
