package mz.sga.ujc.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurtyConfig extends WebSecurityConfigurerAdapter{
    
  private UserPrincipalService userPrincipalService;

  public SecurtyConfig(UserPrincipalService userPrincipalService){
    this.userPrincipalService=userPrincipalService;
  }

  @Bean
  public DaoAuthenticationProvider autProvider(){
    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
    authProvider.setUserDetailsService(userPrincipalService);
    PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
    authProvider.setPasswordEncoder(encoder);
    return authProvider;
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth)throws Exception{
    auth.authenticationProvider(autProvider());
  }

  @Override
  protected void configure(final HttpSecurity http) throws Exception{
    http
            .authorizeRequests()
                .antMatchers("/", "/home").permitAll()
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/account/login")
                .permitAll()
                .and()
            .logout()
                .permitAll();
    ;
  }
}
