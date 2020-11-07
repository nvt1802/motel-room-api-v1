package tainv13.app.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tainv13.app.common.PageCommon;
import tainv13.app.dto.PostDTO;
import tainv13.app.dto.PostDTOPageable;
import tainv13.app.model.Post;
import tainv13.app.service.ImageService;
import tainv13.app.service.PostService;

@CrossOrigin
@RequestMapping("api/discover")
@RestController
public class DiscoverController {

	@Autowired
	private PostService postService;

	@Autowired
	private ImageService imageService;

	@PostMapping("/latest")
	public ResponseEntity<?> discoverLatest(@RequestBody PageCommon pageCommon) {
		Pageable pageable = PageRequest.of(pageCommon.getPage(), pageCommon.getPageSize(),
				Sort.by("post_date").descending());
		Page<Post> page = postService.findAllPostPageableDiscover(pageable);
		List<PostDTO> list = new ArrayList<>();
		for (Post post : page) {
			PostDTO dto = new PostDTO();
			post.getMotelRoom().getMotelId();
			dto.setPost(post);
			dto.setImage(imageService.findImageByMotelId(post.getMotelRoom().getMotelId()));
			list.add(dto);
		}
		return new ResponseEntity<>(new PostDTOPageable(list, page.getTotalPages(), page.getSize()), HttpStatus.OK);
	}

	@PostMapping("/common")
	public ResponseEntity<?> discoverCommon(@RequestBody PageCommon pageCommon) {
		Pageable pageable = PageRequest.of(pageCommon.getPage(), pageCommon.getPageSize(),
				Sort.by("post_view").descending());
		Page<Post> page = postService.findAllPostPageableDiscover(pageable);
		List<PostDTO> list = new ArrayList<>();
		for (Post post : page) {
			PostDTO dto = new PostDTO();
			post.getMotelRoom().getMotelId();
			dto.setPost(post);
			dto.setImage(imageService.findImageByMotelId(post.getMotelRoom().getMotelId()));
			list.add(dto);
		}
		return new ResponseEntity<>(new PostDTOPageable(list, page.getTotalPages(), page.getSize()), HttpStatus.OK);
	}

	@PostMapping("/cheap")
	public ResponseEntity<?> discoverCheap(@RequestBody PageCommon pageCommon) {
		Pageable pageable = PageRequest.of(pageCommon.getPage(), pageCommon.getPageSize());
		Page<Post> page = postService.findAllPostPageableDiscoverCheap(pageable);
		List<PostDTO> list = new ArrayList<>();
		for (Post post : page) {
			PostDTO dto = new PostDTO();
			post.getMotelRoom().getMotelId();
			dto.setPost(post);
			dto.setImage(imageService.findImageByMotelId(post.getMotelRoom().getMotelId()));
			list.add(dto);
		}
		Collections.sort(list, new Comparator<PostDTO>() {
			@Override
			public int compare(PostDTO o1, PostDTO o2) {
				return o1.getPost().getMotelRoom().getPrice() > o2.getPost().getMotelRoom().getPrice() ? 1 : -1;
			}

		});
		return new ResponseEntity<>(new PostDTOPageable(list, page.getTotalPages(), page.getSize()), HttpStatus.OK);
	}
}
