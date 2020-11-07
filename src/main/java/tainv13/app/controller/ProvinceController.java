package tainv13.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tainv13.app.service.ProvinceService;

@CrossOrigin
@RequestMapping("/api/province")
@RestController
public class ProvinceController {

	@Autowired
	private ProvinceService provinceService;

	@GetMapping
	public ResponseEntity<?> findAll() {
		return new ResponseEntity<>(provinceService.findAllProvince(), HttpStatus.OK);
	}

	@GetMapping("/{provinceId}")
	public ResponseEntity<?> findAll(@PathVariable("provinceId") Long provinceId) {
		return new ResponseEntity<>(provinceService.findProvinceById(provinceId), HttpStatus.OK);
	}
}
