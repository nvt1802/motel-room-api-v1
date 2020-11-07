package tainv13.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tainv13.app.service.DistrictService;

@CrossOrigin
@RequestMapping("/api/district")
@RestController
public class DistrictController {

	@Autowired
	private DistrictService districtService;

	@GetMapping
	public ResponseEntity<?> findAll() {
		return new ResponseEntity<>(districtService.findAllDistrict(), HttpStatus.OK);
	}

	@GetMapping("/provinceId/{provinceId}")
	public ResponseEntity<?> findDistrictByProvinceId(@PathVariable("provinceId") long provinceId) {
		return new ResponseEntity<>(districtService.findAllDistrictByProvinceId(provinceId), HttpStatus.OK);
	}

	@GetMapping("/{districtId}")
	public ResponseEntity<?> findDistrictByDistrictId(@PathVariable("districtId") long districtId) {
		return new ResponseEntity<>(districtService.findAllDistrictByDistricId(districtId), HttpStatus.OK);
	}
}
