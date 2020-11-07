package tainv13.app.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import tainv13.app.common.AuthCommon;
import tainv13.app.common.MessageContant;
import tainv13.app.common.PageCommon;
import tainv13.app.common.UsersContant;
import tainv13.app.dto.AccountDTO;
import tainv13.app.model.Account;
import tainv13.app.service.AccountService;

@CrossOrigin
@RequestMapping("/api/account")
@RestController
public class AccountController {

	@Autowired
	private AccountService accountService;

	@Autowired
	private AuthCommon auth;

	@PostMapping("/available")
	public ResponseEntity<?> findAllAccountAvailable(@RequestBody PageCommon pageCommon, HttpServletRequest request) {
		if (auth.checkRole(UsersContant.ADMIN, request)) {
			Pageable pageable = PageRequest.of(pageCommon.getPage(), pageCommon.getPageSize());
			Page<?> accountAvailable = accountService.findAllAccountAvailable(pageable);
			return new ResponseEntity<>(accountAvailable, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>(MessageContant.UNAUTHORIZED, HttpStatus.UNAUTHORIZED);
		}
	}

	@GetMapping("/{username}")
	public ResponseEntity<?> findAccountByUserName(@PathVariable("username") String username) {
		return new ResponseEntity<>(new AccountDTO(accountService.findAccountByUserName(username)), HttpStatus.OK);
	}

	@GetMapping("/getOneAccount/{accountId}")
	public ResponseEntity<?> findAccountByAccountId(@PathVariable("accountId") Long accountId,
			HttpServletRequest request) {
		if (auth.checkRole(UsersContant.ADMIN, request)) {
			return new ResponseEntity<>(new AccountDTO(accountService.findAccountByAccountId(accountId)),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<String>(MessageContant.UNAUTHORIZED, HttpStatus.UNAUTHORIZED);
		}
	}

	@GetMapping("/checkAccount/{username}")
	public ResponseEntity<?> checkUserNameExist(@PathVariable("username") String username) {
		return new ResponseEntity<>(accountService.findAccountByUserName(username) == null ? true : false,
				HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<?> addOneAccount(@RequestBody @Valid Account account) {
		account.setCreateBy(account.getUserName());
		account.setUpdateBy(account.getUserName());
		account.setPassword(new BCryptPasswordEncoder().encode(account.getPassword()));
		account.setAccountStatus(true);
		return new ResponseEntity<>(accountService.addOneAccount(account), HttpStatus.CREATED);
	}

	@PutMapping("/update")
	public ResponseEntity<?> updateAccount(@RequestBody @Valid Account account, HttpServletRequest request) {
		System.out.println(auth.getAccount(request).getAccountId());
		if (auth.checkRole(UsersContant.ADMIN, request)) {
			if (accountService.checkAccountByAccountIdAndUserName(account.getAccountId(), account.getUserName())) {
				Account updateAccount = accountService.findAccountByAccountId(account.getAccountId());
				updateAccount.setBirthday(account.getBirthday());
				updateAccount.setDistrictId(account.getDistrictId());
				updateAccount.setEmail(account.getEmail());
				updateAccount.setGender(account.getGender());
				updateAccount.setName(account.getName());
				updateAccount.setPhone(account.getPhone());
				updateAccount.setProvinceId(account.getProvinceId());
				updateAccount.setRole(account.getRole());
				updateAccount.setUpdateBy(auth.getUserDetails(request).getUsername());
				return new ResponseEntity<>(accountService.updateAccount(updateAccount), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(false, HttpStatus.OK);
			}
		} else {
			return new ResponseEntity<String>(MessageContant.UNAUTHORIZED, HttpStatus.UNAUTHORIZED);
		}

	}

	@PutMapping("/changeInfo")
	public ResponseEntity<?> changeInfo(@RequestBody @Valid Account account) {
		if (accountService.checkAccountByAccountIdAndUserName(account.getAccountId(), account.getUserName())) {
			Account updateAccount = accountService.findAccountByAccountId(account.getAccountId());
			updateAccount.setBirthday(account.getBirthday());
			updateAccount.setDistrictId(account.getDistrictId());
			updateAccount.setEmail(account.getEmail());
			updateAccount.setGender(account.getGender());
			updateAccount.setName(account.getName());
			updateAccount.setPhone(account.getPhone());
			updateAccount.setProvinceId(account.getProvinceId());
			return new ResponseEntity<>(accountService.updateAccount(updateAccount), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(false, HttpStatus.OK);
		}
	}

	@PutMapping("/lockAndUnlock")
	public ResponseEntity<?> lockAccount(@RequestBody Account account, HttpServletRequest request) {
		Account acc = accountService.findAccountByAccountId(account.getAccountId());
		if (auth.checkRole(UsersContant.ADMIN, request)) {
			if (!"admin".equals(acc.getUserName())) {
				return new ResponseEntity<>(accountService.lockAndUnlockPost(account), HttpStatus.OK);
			} else {
				return new ResponseEntity<String>(MessageContant.UNAUTHORIZED, HttpStatus.UNAUTHORIZED);
			}
		} else {
			return new ResponseEntity<String>(MessageContant.UNAUTHORIZED, HttpStatus.UNAUTHORIZED);
		}
	}

	@PostMapping("/multipleDelete")
	public ResponseEntity<?> deleteAccount(@RequestBody List<Long> listAccountId, HttpServletRequest request) {
		if (auth.checkRole(UsersContant.ADMIN, request)) {
			return new ResponseEntity<>(
					accountService.deleteAccount(listAccountId, auth.getUserDetails(request).getUsername()),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<String>(MessageContant.UNAUTHORIZED, HttpStatus.UNAUTHORIZED);
		}
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		return errors;
	}
}
