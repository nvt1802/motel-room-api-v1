package tainv13.app.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RequestMapping("/")
@RestController
public class HelloController {
	@GetMapping
	public ResponseEntity<?> findAccountByUserName(@PathVariable("username") String username) {
		return ResponseEntity.ok("hello world");
	}
}
