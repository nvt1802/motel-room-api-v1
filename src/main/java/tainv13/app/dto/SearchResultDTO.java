package tainv13.app.dto;

import tainv13.app.model.Image;
import tainv13.app.model.Post;

public class SearchResultDTO {
	private Post post;
	private Image image;

	public SearchResultDTO() {
		super();
	}

	public SearchResultDTO(Post post, Image image) {
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

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}
}
