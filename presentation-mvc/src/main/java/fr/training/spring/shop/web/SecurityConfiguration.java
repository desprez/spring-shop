package fr.training.spring.shop.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		auth.inMemoryAuthentication().withUser("user").password(encoder.encode("password")).roles("NORMAL_USER").and()
				.withUser("admin").password(encoder.encode("admin")).roles("NORMAL_USER", "ADMIN");
	}

	@Override
	protected void configure(final HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/webjars/**").permitAll();
		http.csrf().disable().authorizeRequests()
				.antMatchers("/WebMvcConfigurerAdapter/**").hasRole("ADMIN").antMatchers("/anonymous*")
				.anonymous().antMatchers("/login*").permitAll().anyRequest().authenticated().and().formLogin()
				.loginPage("/login").defaultSuccessUrl("/", true)
				.failureUrl("/login?error=true").and().logout().logoutUrl("/logout").logoutSuccessUrl("/login");
	}

}