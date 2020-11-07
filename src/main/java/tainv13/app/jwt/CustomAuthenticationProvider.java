package tainv13.app.jwt;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import tainv13.app.model.Account;
import tainv13.app.service.AccountService;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private AccountService accountService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = authentication.getName();
		String password = authentication.getCredentials().toString();
		Account account = accountService.findAccountByUserName(username);
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		if (account != null && account.getUserName().equals(username)) {
			if (passwordEncoder.matches(password, account.getPassword())) {
				if (account.getAccountStatus() == false) {
					throw new BadCredentialsException("ACCOUNT_LOCKED");
				} else {
					return new UsernamePasswordAuthenticationToken(username, password, Collections.emptyList());
				}
			} else {
				throw new BadCredentialsException("INVALID_PASSWORD");
			}
		} else {
			throw new BadCredentialsException("INVALID_USERNAME");
		}
	}

	@Override
	public boolean supports(Class<?> aClass) {
		return aClass.equals(UsernamePasswordAuthenticationToken.class);
	}
}
