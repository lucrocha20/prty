package br.fatec.prty.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import br.fatec.prty.security.JWTAuthenticationFilter;
import br.fatec.prty.security.JWTAuthorizationFilter;
import br.fatec.prty.security.JWTUtil;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private JWTUtil jwtUtil;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	private static final String[] PUBLIC_MATCHERS = {
			"/eventos/**"
	};
	private static final String[] PUBLIC_MATCHERS_POST = {
			"/usuarios/**"
	};
	private static final String[] PUBLIC_MATCHERS_PUT = {};
	private static final String[] PUBLIC_MATCHERS_DELETE = {};
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable()
			.authorizeRequests()
			.antMatchers("/login").permitAll()
			.antMatchers(HttpMethod.GET, PUBLIC_MATCHERS).permitAll()
			.antMatchers(HttpMethod.POST, PUBLIC_MATCHERS_POST).permitAll()
			.antMatchers(HttpMethod.PUT, PUBLIC_MATCHERS_PUT).permitAll()
			.antMatchers(HttpMethod.DELETE, PUBLIC_MATCHERS_DELETE).permitAll()
			.anyRequest().authenticated().and()
			.addFilter(new JWTAuthenticationFilter(authenticationManager(), jwtUtil))
			.addFilter(new JWTAuthorizationFilter(authenticationManager(), jwtUtil, userDetailsService))
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
		;
	}
	
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
		return source;
	}
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
