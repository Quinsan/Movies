package fi.hh.movies;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import fi.hh.movies.web.UserDetailServiceImpl;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true) //että ei voi poistaa userina osoitteista
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	//injektoidaan userdetailservice, securityclass .webissä, jonka voi suoraa kopioida, perus securityjuttu
    @Autowired
    private UserDetailServiceImpl userDetailsService;
	
	//määritellään mitkä osoitteet tarvitsee authorizationia
	@Override
	protected void configure(HttpSecurity http) throws Exception {
        http
        .authorizeRequests().antMatchers("/css/**", "/api/**", "/movies", "/**").permitAll() // jos tarvitaan css. tämä pitää olla, voi olla vaikka public myös
        .and()
        .authorizeRequests().anyRequest().authenticated()
        .and()
      .formLogin()
          .loginPage("/login")
          .defaultSuccessUrl("/movielist")
          .permitAll()
          .and()
      .logout()
          .permitAll();
	}
	
	//tämä taas tekee userin classista, tekee automaatissesti encryptionin
	 @Autowired
	    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	    }
}

