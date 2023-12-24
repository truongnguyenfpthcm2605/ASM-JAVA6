package com.poly.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.thymeleaf.extras.springsecurity6.dialect.SpringSecurityDialect;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class AuthConfig {
	


	@Bean
	PasswordEncoder passwordEncoder() {
		return  new BCryptPasswordEncoder();
	}

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.cors(AbstractHttpConfigurer::disable).csrf(AbstractHttpConfigurer::disable);
		
		http.authorizeHttpRequests(auth -> {
			auth.requestMatchers("/contact/**","/cart/order/**").authenticated();
			auth.requestMatchers("/admin/**").hasAnyAuthority("ROLE_USER","ROLE_ADMIN");
			auth.anyRequest().permitAll();
		}).exceptionHandling(e -> {
			e.accessDeniedPage("/auth/access/denied");
		})
		.formLogin(login -> {
			login.loginPage("/auth/login/form");
			login.loginProcessingUrl("/auth/login");
			login.defaultSuccessUrl("/auth/login/success", false);
			login.failureUrl("/auth/login/error");
			login.usernameParameter("username");
			login.passwordParameter("password");		
		})
		.rememberMe(rm -> {
			rm.rememberMeParameter("remember");
			rm.tokenValiditySeconds(86400);
			
		})
		.logout(logout -> {
			logout.logoutUrl("/auth/logoff");
			logout.logoutSuccessUrl("/auth/logoff/success");
			logout.addLogoutHandler(new SecurityContextLogoutHandler());
		})
		.oauth2Login(s ->{
			s.loginPage("/auth/login/form");
			s.defaultSuccessUrl("/oauth2/login/success",true);
			s.failureUrl("/auth/login/error");
			s.authorizationEndpoint(a -> {
				a.baseUri("/oauth2/authorization");
			});
		});
		
		return http.build();

	}

	@Bean
	AuthenticationProvider authenticationProvider(AccountDetailService accountDetailService) {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(accountDetailService);
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
	}

	@Bean
	CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.addAllowedOrigin("*");
		config.addAllowedMethod("*");
		config.addAllowedHeader("*");
		config.setAllowCredentials(true);
		source.registerCorsConfiguration("/**", config);
		return new CorsFilter(source);
	}

	@Bean
	public SpringSecurityDialect springSecurityDialect() {
		return new SpringSecurityDialect();
	}

}

