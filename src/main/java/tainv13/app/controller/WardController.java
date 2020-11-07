package tainv13.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tainv13.app.service.WardService;

@CrossOrigin
@RequestMapping("/api/ward")
@RestController
public class WardController {

	@Autowired
	private WardService wardService;

	@GetMapping
	public ResponseEntity<?> findAll() {
		return new ResponseEntity<>(wardService.findAllWard(), HttpStatus.OK);
	}

	@GetMapping("/{districtId}")
	public ResponseEntity<?> findWardByDistricId(@PathVariable("districtId") Long districtId) {
		return new ResponseEntity<>(wardService.findWardByDistricId(districtId), HttpStatus.OK);
	}
}
