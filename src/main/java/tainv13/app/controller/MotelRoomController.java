package tainv13.app.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tainv13.app.common.AuthCommon;
import tainv13.app.common.PageCommon;
import tainv13.app.model.Account;
import tainv13.app.model.MotelRoom;
import tainv13.app.service.AccountService;
import tainv13.app.service.MotelRoomService;

@CrossOrigin
@RequestMapping("/api/motelRoom")
@RestController
public class MotelRoomController {

	@Autowired
	private AuthCommon auth;

	@Autowired
	private AccountService accountService;

	@Autowired
	private MotelRoomService motelRoomService;

	@GetMapping
	public ResponseEntity<?> findAllMotelRoom() {
		return new ResponseEntity<>(motelRoomService.findAll(), HttpStatus.OK);
	}

	@GetMapping("/{motelId}")
	public ResponseEntity<?> findMotelRoomById(@PathVariable("motelId") Long motelId, HttpServletRequest request) {
		Account account = accountService.findAccountByUserName(auth.getUserDetails(request).getUsername());
		return new ResponseEntity<>(motelRoomService.findMotelRoomById(motelId, account.getAccountId()), HttpStatus.OK);
	}

	@GetMapping("/accountId/{accountId}")
	public ResponseEntity<?> findMotelRoomByAccountId(@PathVariable("accountId") Long accountId,
			HttpServletRequest request) {
		if (auth.getAccount(request).getAccountId() == accountId) {
			return new ResponseEntity<>(motelRoomService.findMotelRoomByAccountId(accountId), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/accountId/{accountId}/{postId}")
	public ResponseEntity<?> findMotelRoomByAccountIdForEdit(@PathVariable("accountId") Long accountId,
			@PathVariable("postId") Long postId, HttpServletRequest request) {
		if (auth.getAccount(request).getAccountId() == accountId) {
			return new ResponseEntity<>(motelRoomService.findMotelRoomByAccountIdForEdit(accountId, postId),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping
	public ResponseEntity<?> createMotelRoom(@RequestBody @Valid MotelRoom motelRoom, HttpServletRequest request) {
		Account account = accountService.findAccountByUserName(auth.getUserDetails(request).getUsername());
		motelRoom.setAccount(account);
		motelRoom.setCreateAt(new Date());
		motelRoom.setUpdateAt(new Date());
		motelRoom.setCreateBy(auth.getUserDetails(request).getUsername());
		motelRoom.setUpdateBy(auth.getUserDetails(request).getUsername());
		return new ResponseEntity<>(motelRoomService.createMotelRoom(motelRoom), HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<?> updateMotelRoom(@RequestBody @Valid MotelRoom motelRoom, HttpServletRequest request) {
		Account account = auth.getAccount(request);
		if (motelRoomService.findMotelRoomById(motelRoom.getMotelId(), account.getAccountId()) != null) {
			motelRoom.setUpdateBy(auth.getUserDetails(request).getUsername());
			motelRoom.setUpdateAt(new Date());
			return new ResponseEntity<>(motelRoomService.updateMotelRoom(motelRoom), HttpStatus.OK);
		}
		return new ResponseEntity<>(false, HttpStatus.NOT_MODIFIED);
	}

	@PostMapping("/pageable")
	public ResponseEntity<?> findRoomByAccountIdPageable(@RequestBody PageCommon pageCommon,
			HttpServletRequest request) {
		Pageable pageable = PageRequest.of(pageCommon.getPage(), pageCommon.getPageSize());
		String username = auth.getUserDetails(request).getUsername();
		Long accountId = accountService.findAccountByUserName(username).getAccountId();
		return new ResponseEntity<>(motelRoomService.findRoomByAccountIdPageable(pageable, accountId), HttpStatus.OK);
	}

	@PostMapping("/multipleDelete")
	public ResponseEntity<?> deleteMotelRoom(@RequestBody List<Long> listMotelId, HttpServletRequest request) {
		Account account = auth.getAccount(request);
		return new ResponseEntity<>(motelRoomService.deleteMotelRoom(listMotelId, account.getUserName()),
				HttpStatus.OK);
	}
}
