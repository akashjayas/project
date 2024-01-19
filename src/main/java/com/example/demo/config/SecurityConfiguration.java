package com.example.demo.config;

import static org.springframework.security.config.Customizer.withDefaults;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import jakarta.servlet.http.HttpServletRequest;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
	@Autowired
	private CustomAuthSuccessHandler successHandler;
	@Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	@Bean
	public UserDetailsService getDetailsService() {
		return new CustomUserDetailsService();
	}
	 @Bean
	    public DaoAuthenticationProvider authenticationProvider() {
	        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
	        daoAuthenticationProvider.setUserDetailsService(getDetailsService());
	        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
	        return daoAuthenticationProvider;
	    }
	 

	
    @Bean
    public SecurityFilterChain adminSecurityFilterChain(HttpSecurity http) throws Exception {
    	http.sessionManagement(management->management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

        .authorizeHttpRequests(request -> request
        		.requestMatchers("/login").permitAll()
                .requestMatchers("/Admin/**").hasRole("ADMIN")
                .anyRequest().authenticated())
        .csrf(csrf -> csrf.disable())
        .cors(cors->cors.configurationSource(corsConfigurationSource()))
        

        .formLogin(form -> form.loginPage("/login").loginProcessingUrl("/login")
				.permitAll())
              .logout(form -> form.invalidateHttpSession(true).clearAuthentication(true)
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		.logoutSuccessUrl("/login?logout").permitAll());
         
        return http.build();
    }
//    @Bean
//    @Order(2)
//    public SecurityFilterChain userecurityFilterChain(HttpSecurity http) throws Exception {
//    	http.csrf(c -> c.disable())
//
//        .authorizeHttpRequests(request -> request
//               .requestMatchers("/User/**").hasRole("USER")
//                .anyRequest().authenticated())
//    	.formLogin(form -> form.loginPage("/login-user").loginProcessingUrl("/login")
//				.successHandler(successHandler).permitAll())
//              .logout(form -> form.invalidateHttpSession(true).clearAuthentication(true)
//		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//		.logoutSuccessUrl("/login?logout").permitAll());
//
//        return http.build();
//    }
//    
	private CorsConfigurationSource corsConfigurationSource() {
		// TODO Auto-generated meth
		return new  CorsConfigurationSource() {
			
			@Override
			public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
				CorsConfiguration cfg=new CorsConfiguration();
				cfg.setAllowedOrigins(Arrays.asList(
						"http://localhost:3000/"));
				cfg.setAllowedMethods(Collections.singletonList("*"));
				cfg.setAllowCredentials(true);
				cfg.setMaxAge(3600L);
				return null;
			}
		};
	}


   

	

    
}
