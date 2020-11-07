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
import tainv13.app.common.MessageContant;
import tainv13.app.common.PageCommon;
import tainv13.app.common.UsersContant;
import tainv13.app.model.Account;
import tainv13.app.model.Post;
import tainv13.app.service.AccountService;
import tainv13.app.service.PostService;

@CrossOrigin
@RequestMapping("/api/post")
@RestController
public class PostController {

	@Autowired
	private AuthCommon auth;

	@Autowired
	private PostService postService;

	@Autowired
	private AccountService accountService;

	@GetMapping
	public ResponseEntity<?> findAll() {
		return new ResponseEntity<>(postService.findAllPost(), HttpStatus.OK);
	}

	@GetMapping("/{postId}")
	public ResponseEntity<?> findPostById(@PathVariable("postId") Long postId, HttpServletRequest request) {
		Post post = postService.findPostById(postId);
		if (auth.checkRole(UsersContant.ADMIN, request)) {
			return new ResponseEntity<>(post, HttpStatus.OK);
		} else {
			Account account = accountService.findAccountByUserName(auth.getUserDetails(request).getUsername());
			if (post.getAccount().getAccountId() == account.getAccountId()) {
				return new ResponseEntity<>(post, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
			}
		}
	}

	@GetMapping("/viewPost/{postId}")
	public ResponseEntity<?> viewPostById(@PathVariable("postId") Long postId) {
		postService.increaseView(postId);
		return new ResponseEntity<>(postService.findPostById(postId), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<?> createPost(@RequestBody @Valid Post post, HttpServletRequest request) {
		Account account = auth.getAccount(request);
		post.setAccount(account);
		post.setPostStatus(true);
		post.setCreateBy(account.getUserName());
		post.setUpdateBy(account.getUserName());
		return new ResponseEntity<>(postService.createPost(post), HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<?> updatePost(@RequestBody @Valid Post post, HttpServletRequest request) {
		Account account = auth.getAccount(request);
		post.setAccount(account);
		if (post.getAccount().getAccountId() == account.getAccountId() || auth.checkRole(UsersContant.ADMIN, request)) {
			Post post2 = postService.findPostById(post.getPostId());
			post.setPostStatus(post2.isPostStatus());
			post.setPostDate(post2.getPostDate());
			post.setCreateBy(post2.getCreateBy());
			post.setUpdateBy(account.getUserName());
			post.setUpdateAt(new Date());
			return new ResponseEntity<>(postService.updatePost(post), HttpStatus.OK);
		}
		return new ResponseEntity<>(false, HttpStatus.NOT_MODIFIED);
	}

	@PostMapping("/pageable")
	public ResponseEntity<?> findAllPageable(@RequestBody PageCommon pageCommon, HttpServletRequest request) {
		Pageable pageable = PageRequest.of(pageCommon.getPage(), pageCommon.getPageSize());
		String username = auth.getUserDetails(request).getUsername();
		return new ResponseEntity<>(postService.findAllPostPageable(
				accountService.findAccountByUserName(username).getAccountId(), pageable), HttpStatus.OK);

	}

	@PostMapping("/pageableAvailable")
	public ResponseEntity<?> findAllPageableAvailable(@RequestBody PageCommon pageCommon, HttpServletRequest request) {
		Pageable pageable = PageRequest.of(pageCommon.getPage(), pageCommon.getPageSize());
		return new ResponseEntity<>(postService.findAllPostPageable(pageable), HttpStatus.OK);

	}

	@PostMapping("/multipleDelete")
	public ResponseEntity<?> deleteMotelRoom(@RequestBody List<Long> listPostId, HttpServletRequest request) {
		Account account = auth.getAccount(request);
		return new ResponseEntity<>(postService.deletePost(listPostId, account.getUserName()), HttpStatus.OK);
	}

	@PutMapping("/lockAndUnlock")
	public ResponseEntity<?> lockPost(@RequestBody Post post, HttpServletRequest request) {
		if (auth.checkRole(UsersContant.ADMIN, request)) {
			return new ResponseEntity<>(postService.lockAndUnlockPost(post), HttpStatus.OK);
		} else {
			return new ResponseEntity<String>(MessageContant.UNAUTHORIZED, HttpStatus.UNAUTHORIZED);
		}

	}
}
