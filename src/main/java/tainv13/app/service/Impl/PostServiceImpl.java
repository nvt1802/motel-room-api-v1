package tainv13.app.service.Impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import tainv13.app.model.Account;
import tainv13.app.model.Post;
import tainv13.app.repository.AccountRepository;
import tainv13.app.repository.PostRepository;
import tainv13.app.service.MotelRoomService;
import tainv13.app.service.PostService;

@Service
public class PostServiceImpl extends PostService {

	@Autowired
	private PostRepository postRepository;

	@Autowired
	private MotelRoomService motelRoomService;

	@Autowired
	private AccountRepository accountRepository;

	@Override
	public List<Post> findAllPost() {
		return postRepository.findAll();
	}

	@Override
	public Post findPostById(Long postId) {
		return postRepository.findPostById(postId);
	}

	@Override
	public Post createPost(Post post) {
		try {
			return postRepository.save(post);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Page<Post> findAllPostPageable(Long accountId, Pageable pageable) {
		return postRepository.findAllPostPageable(accountId, pageable);
	}

	@Override
	public Page<Post> findAllPostPageable(Pageable pageable) {
		return postRepository.findAllPostPageable(pageable);
	}

	@Override
	public Boolean deletePost(List<Long> listPostId, String deleteBy) {
		try {
			Account account = accountRepository.findByUserName(deleteBy);
			for (Long id : listPostId) {
				Post post = postRepository.getOne(id);
				if (post != null
						&& (account.getAccountId() == post.getAccount().getAccountId() || account.getRole() == 1)) {
					post.setDeleteAt(new Date());
					post.setDeleteBy(deleteBy);
					postRepository.save(post);
				} else {
					return false;
				}
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Post lockAndUnlockPost(Post post) {
		try {
			Post post2 = postRepository.findPostById(post.getPostId());
			boolean status = post2.isPostStatus();
			post2.setPostStatus(!status);
			return postRepository.save(post2);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Boolean increaseView(Long postId) {
		try {
			Post post = postRepository.findPostById(postId);
			post.setPostView(post.getPostView() + 1);
			postRepository.save(post);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Page<Post> searchPost(Long provinceId, Long districtId, Pageable pageable) {
		return postRepository.searchPost(motelRoomService.searchMotelRoom(provinceId, districtId), pageable);
	}

	@Override
	public Page<Post> searchPostAdvance(List<Long> listCriteriaId, Long price, Long acreage, Pageable pageable) {
		List<Long> listId = motelRoomService.findMotelIdByListCriteriaId(listCriteriaId, listCriteriaId.size());
		List<Long> list = motelRoomService.findMotelIdByAcreateAndPrice(listId, price, acreage, listId.size());
		return postRepository.searchPostAdvance(list, pageable);
	}

	@Override
	public Page<Post> findAllPostPageableDiscover(Pageable pageable) {
		return postRepository.findAllPostPageableDiscover(pageable);
	}

	@Override
	public Page<Post> findAllPostPageableDiscoverCheap(Pageable pageable) {
		return postRepository.findAllPostPageableDiscoverCheap(pageable);
	}

	@Override
	public Post updatePost(Post post) {
		try {
			return postRepository.save(post);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Boolean deletePost(Long motelId, Long accountId, String deleteBy) {
		try {
			List<Post> list = postRepository.findPostByMotelIdAndAccountId(motelId, accountId);
			for (Post post : list) {
				post.setUpdateAt(new Date());
				post.setUpdateBy(deleteBy);
				post.setDeleteAt(new Date());
				post.setDeleteBy(deleteBy);
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
