package com.example.springsecurityjdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource ds;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // set your configuration on the auth object
        // without database - in memory
//        auth.inMemoryAuthentication()
//                .withUser("user")
//                .password("user")
//                .roles("user").and().withUser("admin").password("admin").roles("admin");

        // using H2 embeded database
        // spring automatically instantiates H2 db from dependency and creates the schema tables.
//        auth.jdbcAuthentication()
//                .dataSource(ds)
//                .withDefaultSchema()
//                .withUser(User.withUsername("user")
//                        .password("user")
//                        .roles("user")
//                ).withUser(User.withUsername("admin")
//                        .password("admin")
//                        .roles("admin")
//                );
        // if you want credentials to be already stored in spring default schema
        // it uses the credentials in users and authorities table as in schema.sql and data.sql
//        auth.jdbcAuthentication()
//                .dataSource(ds)
//                .usersByUsernameQuery("select username,password, enabled from users where username=?")
//                .authoritiesByUsernameQuery("select username, authority from authorities where" +
//                        "username = ?");
        /*
        the above are default queries for the default schema but we can set custom queries for our custom
        database security tables. But if you maintain your own tables for security, you can override the queries above.
         */
        auth.jdbcAuthentication()
                .dataSource(ds);
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/admin").hasRole("admin")
                .antMatchers("/user").hasAnyRole("user", "admin")
                .antMatchers("/").permitAll()
                .and().formLogin();
    }
}
