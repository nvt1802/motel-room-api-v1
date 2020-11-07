package tainv13.app.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import tainv13.app.model.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

	String QUERY_SEARCH = "SELECT * FROM post WHERE delete_at is null and motel_id in :listMotelId and post_status = 1 ORDER BY post_date DESC";

	String QUERY_SEARCH_ADVANCE = "SELECT * FROM post WHERE delete_at is null and motel_id in :listMotelId and post_status = 1 ORDER BY post_date DESC";

	@Query(nativeQuery = true, value = "SELECT * FROM post WHERE post_id = :postId AND delete_at IS NULL")
	public Post findPostById(@Param("postId") Long postId);

	@Query(nativeQuery = true, value = "SELECT * FROM post WHERE account_id = :accountId AND delete_at IS NULL")
	public Page<Post> findAllPostPageable(@Param("accountId") Long accountId, Pageable pageable);

	@Query(nativeQuery = true, value = "SELECT * FROM post WHERE delete_at IS NULL")
	public Page<Post> findAllPostPageable(Pageable pageable);

	@Query(nativeQuery = true, value = "SELECT * FROM post WHERE delete_at IS NULL and post_status = 1")
	public Page<Post> findAllPostPageableDiscover(Pageable pageable);

	@Query(nativeQuery = true, value = "SELECT * FROM post p INNER JOIN motel_room r ON p.motel_id = r.motel_id WHERE p.delete_at IS NULL and p.post_status = 1 ORDER BY r.price DESC")
	public Page<Post> findAllPostPageableDiscoverCheap(Pageable pageable);

	@Query(nativeQuery = true, value = QUERY_SEARCH)
	public Page<Post> searchPost(@Param("listMotelId") List<Long> listMotelId, Pageable pageable);

	@Query(nativeQuery = true, value = QUERY_SEARCH_ADVANCE)
	public Page<Post> searchPostAdvance(@Param("listMotelId") List<Long> listMotelId, Pageable pageable);

	@Query(nativeQuery = true, value = "SELECT * FROM post WHERE motel_id = :motelId and account_id = :accountId")
	public List<Post> findPostByMotelIdAndAccountId(@Param("motelId") Long motelId, @Param("accountId") Long accountId);
}
