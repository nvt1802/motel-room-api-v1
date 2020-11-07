package tainv13.app.dto;

import java.util.List;

import tainv13.app.model.Image;
import tainv13.app.model.Post;

public class PostDTO {
	private Post post;
	private List<Image> image;

	public PostDTO() {
		super();
	}

	public PostDTO(Post post, List<Image> image) {
		super();
		this.post = post;
		this.image = image;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public List<Image> getImage() {
		return image;
	}

	public void setImage(List<Image> image) {
		this.image = image;
	}
}
