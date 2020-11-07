package tainv13.app.common;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import tainv13.app.jwt.JwtTokenUtil;
import tainv13.app.model.Account;
import tainv13.app.model.JwtUserDetails;
import tainv13.app.service.AccountService;

@Component
public class AuthCommon {

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private AccountService accountService;

	@Value("${jwt.http.request.header}")
	private String tokenHeader;

	@Autowired
	private UserDetailsService jwtInMemoryUserDetailsService;

	public JwtUserDetails getUserDetails(HttpServletRequest request) {
		String authToken = request.getHeader(tokenHeader);
		final String token = authToken.substring(7);
		String username = jwtTokenUtil.getUsernameFromToken(token);
		JwtUserDetails user = (JwtUserDetails) jwtInMemoryUserDetailsService.loadUserByUsername(username);
		return user;
	}

	public Boolean checkRole(String role, HttpServletRequest request) {
		String authToken = request.getHeader(tokenHeader);
		final String token = authToken.substring(7);
		String username = jwtTokenUtil.getUsernameFromToken(token);
		JwtUserDetails user = (JwtUserDetails) jwtInMemoryUserDetailsService.loadUserByUsername(username);
		if (role.equals(user.getAuthorities().toString())) {
			return true;
		} else {
			return false;
		}
	}

	public Account getAccount(HttpServletRequest request) {
		return accountService.findAccountByUserName(getUserDetails(request).getUsername());
	}
}
