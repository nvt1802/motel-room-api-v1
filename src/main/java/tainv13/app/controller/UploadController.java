package tainv13.app.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import tainv13.app.common.UploadCommon;
import tainv13.app.dto.UploadResponse;

@CrossOrigin
@RequestMapping("/api")
@Controller
public class UploadController {

	@PostMapping("/upload")
	public ResponseEntity<?> uploadToLocalFileSystem(@RequestPart("file") MultipartFile file) {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		Path path = Paths.get(UploadCommon.getPath() + fileName);
		try {
			Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
		}
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/download/")
				.path(fileName).toUriString();
		return ResponseEntity.ok(new UploadResponse(fileName, file.getSize(), fileDownloadUri));
	}

	@PostMapping("/multi-upload")
	public ResponseEntity<?> multiUpload(@RequestParam("files") MultipartFile[] files) {
		List<UploadResponse> uploadResponses = new ArrayList<>();
		for (MultipartFile file : files) {
			String fileName = StringUtils.cleanPath(file.getOriginalFilename());
			Path path = Paths.get(UploadCommon.getPath() + fileName);
			try {
				Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException e) {
				return new ResponseEntity<String>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
			}
			String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/download/")
					.path(fileName).toUriString();
			uploadResponses.add(new UploadResponse(fileName, file.getSize(), fileDownloadUri));
		}
		return ResponseEntity.ok(uploadResponses);
	}

	@GetMapping("/download/{fileName}")
	public ResponseEntity<?> downloadFileFromLocal(@PathVariable("fileName") String fileName) {
		Path path = Paths.get(UploadCommon.getPath() + fileName);
		Resource resource = null;
		try {
			resource = new UrlResource(path.toUri());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok().contentType(MediaType.MULTIPART_FORM_DATA)
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
				.body(resource);
	}
}
