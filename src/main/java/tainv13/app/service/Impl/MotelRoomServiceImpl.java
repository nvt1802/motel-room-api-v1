package tainv13.app.service.Impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import tainv13.app.model.MotelRoom;
import tainv13.app.repository.MotelRoomRepository;
import tainv13.app.service.MotelRoomService;
import tainv13.app.service.PostService;

@Service
public class MotelRoomServiceImpl extends MotelRoomService {

	@Autowired
	private MotelRoomRepository motelRoomRepository;

	@Autowired
	private PostService postService;

	@Override
	public List<MotelRoom> findAll() {
		return motelRoomRepository.findAll();
	}

	@Override
	public MotelRoom createMotelRoom(MotelRoom motelRoom) {
		try {
			return motelRoomRepository.save(motelRoom);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public MotelRoom updateMotelRoom(MotelRoom motelRoom) {
		try {
			return motelRoomRepository.save(motelRoom);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Page<MotelRoom> findRoomByAccountIdPageable(Pageable pageable, Long accountId) {
		return motelRoomRepository.findRoomByAccountIDAndDeleteAtIsNull(pageable, accountId);
	}

	@Override
	public Boolean deleteMotelRoom(List<Long> listMotelId, String deleteBy) {
		try {
			for (Long id : listMotelId) {
				MotelRoom motelRoom = motelRoomRepository.getOne(id);
				if (motelRoom != null) {
					if (!postService.deletePost(id, motelRoom.getAccount().getAccountId(), deleteBy)) {
						return false;
					} else {
						motelRoom.setDeleteAt(new Date());
						motelRoom.setDeleteBy(deleteBy);
						motelRoomRepository.save(motelRoom);
					}
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
	public MotelRoom findMotelRoomById(Long motelId, Long accountId) {
		return motelRoomRepository.findMotelRoomById(motelId, accountId);
	}

	@Override
	public List<MotelRoom> findMotelRoomByAccountId(Long accountId) {
		return motelRoomRepository.findMotelRoomByAccountId(accountId);
	}

	@Override
	public List<Long> searchMotelRoom(Long provinceId, Long districtId) {
		return motelRoomRepository.searchMotelRoom(provinceId, districtId);
	}

	@Override
	public List<Long> findMotelIdByListCriteriaId(List<Long> listCriteriaId, int count) {
		return motelRoomRepository.findMotelIdByListCriteriaId(listCriteriaId, count);
	}

	@Override
	public List<Long> findMotelIdByAcreateAndPrice(List<Long> listMotelId, Long price, Long acreage, int count) {
		return motelRoomRepository.findMotelIdByAcreateAndPrice(listMotelId, price, acreage, count);
	}

	@Override
	public List<MotelRoom> findMotelRoomByAccountIdForEdit(Long accountId, Long postId) {
		return motelRoomRepository.findMotelRoomByAccountIdForEdit(accountId, postId);
	}
}
