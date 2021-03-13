package tainv13.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tainv13.app.model.Account;
import tainv13.app.repository.AccountRepository;

@CrossOrigin
@RequestMapping("/")
@RestController
public class HelloController {
	@Autowired
	private AccountRepository accountRepository;

	@GetMapping
	public ResponseEntity<?> findAccountByUserName() {
		return ResponseEntity.ok(accountRepository.findAll());
	}

	@GetMapping("/createAdmin")
	public ResponseEntity<?> createAdmin() {
		Account account = new Account();
		account.setAccountId(1L);
		account.setUserName("admin");
		account.setPassword("123");
		account.setName("Nguyễn Văn Tài");
		account.setPhone("0971962464");
		account.setEmail("tainguyen6600@gmail.com");
		accountRepository.save(account);
		return ResponseEntity.ok("created !");
	}
}
