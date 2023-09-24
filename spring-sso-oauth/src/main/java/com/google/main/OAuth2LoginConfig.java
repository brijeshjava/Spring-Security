package com.google.main;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class OAuth2LoginConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests(authorize -> authorize
				.anyRequest().authenticated()
			)
			.oauth2Login();
		return http.build();
	}

	/*
	 * @Bean public ClientRegistrationRepository clientRegistrationRepository() {
	 * return new
	 * InMemoryClientRegistrationRepository(this.googleClientRegistration()); }
	 * 
	 * @Bean public OAuth2AuthorizedClientService authorizedClientService(
	 * ClientRegistrationRepository clientRegistrationRepository) { return new
	 * InMemoryOAuth2AuthorizedClientService(clientRegistrationRepository); }
	 * 
	 * @Bean public OAuth2AuthorizedClientRepository authorizedClientRepository(
	 * OAuth2AuthorizedClientService authorizedClientService) { return new
	 * AuthenticatedPrincipalOAuth2AuthorizedClientRepository(
	 * authorizedClientService); }
	 * 
	 * private ClientRegistration googleClientRegistration() { return
	 * CommonOAuth2Provider.GOOGLE.getBuilder("google")
	 * .clientId("google-client-id") .clientSecret("google-client-secret") .build();
	 * }
	 */
}