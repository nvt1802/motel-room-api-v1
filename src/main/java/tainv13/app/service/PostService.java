package tainv13.app.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import tainv13.app.model.Post;

public abstract class PostService {
	public abstract List<Post> findAllPost();

	public abstract Post findPostById(Long postId);

	public abstract Post createPost(Post post);

	public abstract Post updatePost(Post post);

	public abstract Page<Post> findAllPostPageable(Long accountId, Pageable pageable);

	public abstract Page<Post> findAllPostPageable(Pageable pageable);

	public abstract Page<Post> findAllPostPageableDiscover(Pageable pageable);

	public abstract Page<Post> findAllPostPageableDiscoverCheap(Pageable pageable);

	public abstract Boolean deletePost(List<Long> listPostId, String deleteBy);

	public abstract Post lockAndUnlockPost(Post post);

	public abstract Boolean increaseView(Long postId);

	public abstract Page<Post> searchPost(Long provinceId, Long districtId, Pageable pageable);

	public abstract Page<Post> searchPostAdvance(List<Long> listCriteriaId, Long price, Long acreage,
			Pageable pageable);

	public abstract Boolean deletePost(Long motelId, Long accountId, String deleteBy);
}
