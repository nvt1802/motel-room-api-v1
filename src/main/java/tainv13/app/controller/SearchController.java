package tainv13.app.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tainv13.app.dto.SearchDTO;
import tainv13.app.model.Image;
import tainv13.app.model.Post;
import tainv13.app.service.ImageService;
import tainv13.app.service.PostService;

@CrossOrigin
@RequestMapping("/api/search")
@RestController
public class SearchController {

	@Autowired
	private PostService postService;

	@Autowired
	private ImageService imageService;

	@PostMapping
	public ResponseEntity<?> search(@RequestBody SearchDTO dto) {
		Pageable pageable = PageRequest.of(dto.getPageCommon().getPage(), dto.getPageCommon().getPageSize());
		if (dto.isOptionAdvance()) {
			return new ResponseEntity<>(
					postService.searchPostAdvance(dto.getListCriteriaId(), dto.getPrice(), dto.getAcreage(), pageable),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(postService.searchPost(dto.getProvinceId(), dto.getDistrictId(), pageable),
					HttpStatus.OK);
		}
	}

	@PostMapping("/image")
	public ResponseEntity<?> searchImage(@RequestBody SearchDTO dto) {
		Pageable pageable = PageRequest.of(dto.getPageCommon().getPage(), dto.getPageCommon().getPageSize());
		List<Image> listImage = new ArrayList<>();
		if (dto.isOptionAdvance()) {
			Page<Post> searchPostAdvance = postService.searchPostAdvance(dto.getListCriteriaId(), dto.getPrice(),
					dto.getAcreage(), pageable);
			for (Post post : searchPostAdvance) {
				listImage.add(imageService.findImageByMotelId(post.getMotelRoom().getMotelId()).get(0));
			}
			return new ResponseEntity<>(listImage, HttpStatus.OK);
		} else {
			Page<Post> searchPost = postService.searchPost(dto.getProvinceId(), dto.getDistrictId(), pageable);
			for (Post post : searchPost) {
				listImage.add(imageService.findImageByMotelId(post.getMotelRoom().getMotelId()).get(0));
			}
			return new ResponseEntity<>(listImage, HttpStatus.OK);
		}
	}
}
