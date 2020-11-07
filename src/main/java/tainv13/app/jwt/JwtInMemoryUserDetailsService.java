package tainv13.app.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import tainv13.app.model.Account;
import tainv13.app.model.JwtUserDetails;
import tainv13.app.service.AccountService;

@Service
public class JwtInMemoryUserDetailsService implements UserDetailsService {
	@Autowired
	private AccountService accountService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Account account = accountService.findAccountByUserName(username);

		if (account != null && username.equals(account.getUserName())) {
			return new JwtUserDetails(account.getAccountId(), username, account.getPassword(),
					account.getRole() == 1 ? "ROLE_ADMIN" : "ROLE_USER");
		} else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
	}

}
//"$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6"
// $2a$10$50FLR20dNuJN.CYX0Dbf0.nBLgmGt0RKf8k4wbYsn00SaJUym1iTS