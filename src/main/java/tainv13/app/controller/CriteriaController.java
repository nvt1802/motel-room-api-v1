package tainv13.app.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tainv13.app.common.AuthCommon;
import tainv13.app.common.MessageContant;
import tainv13.app.common.PageCommon;
import tainv13.app.common.UsersContant;
import tainv13.app.model.Criteria;
import tainv13.app.service.CriteriaService;

@CrossOrigin
@RequestMapping("/api/criteria")
@RestController
public class CriteriaController {

	@Autowired
	private AuthCommon auth;

	@Autowired
	private CriteriaService criteriaService;

	@GetMapping
	public ResponseEntity<?> findAll() {
		return new ResponseEntity<>(criteriaService.findAllCriteria(), HttpStatus.OK);
	}

	@PostMapping("/multiple")
	public ResponseEntity<?> findCriteriaByListCriteriaId(@RequestBody List<Long> listId) {
		return new ResponseEntity<>(criteriaService.findCriteriaByListCriteriaId(listId), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<?> create(@RequestBody Criteria criteria, HttpServletRequest request) {
		if (auth.checkRole(UsersContant.ADMIN, request)) {
			return new ResponseEntity<>(criteriaService.createCriteria(criteria), HttpStatus.OK);
		} else {
			return new ResponseEntity<String>(MessageContant.UNAUTHORIZED, HttpStatus.UNAUTHORIZED);
		}
	}

	@PostMapping("/pageable")
	public ResponseEntity<?> findAllPageable(@RequestBody PageCommon pageCommon, HttpServletRequest request) {
		if (auth.checkRole(UsersContant.ADMIN, request)) {
			Pageable pageable = PageRequest.of(pageCommon.getPage(), pageCommon.getPageSize());
			return new ResponseEntity<>(criteriaService.findAllCriteria(pageable), HttpStatus.OK);
		} else {
			return new ResponseEntity<String>(MessageContant.UNAUTHORIZED, HttpStatus.UNAUTHORIZED);
		}
	}

	@PutMapping
	public ResponseEntity<?> updateCriteria(@RequestBody Criteria criteria, HttpServletRequest request) {
		if (auth.checkRole(UsersContant.ADMIN, request)) {
			return new ResponseEntity<>(criteriaService.updateCriteria(criteria), HttpStatus.OK);
		} else {
			return new ResponseEntity<String>(MessageContant.UNAUTHORIZED, HttpStatus.UNAUTHORIZED);
		}
	}

	@PostMapping("/multipleDelete")
	public ResponseEntity<?> deleteCriteria(@RequestBody List<Long> listCriteriaId, HttpServletRequest request) {
		if (auth.checkRole(UsersContant.ADMIN, request)) {
			return new ResponseEntity<>(criteriaService.deleteCriteria(listCriteriaId), HttpStatus.OK);
		} else {
			return new ResponseEntity<String>(MessageContant.UNAUTHORIZED, HttpStatus.UNAUTHORIZED);
		}
	}
}
