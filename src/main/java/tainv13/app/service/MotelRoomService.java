package tainv13.app.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import tainv13.app.model.MotelRoom;

public abstract class MotelRoomService {
	public abstract List<MotelRoom> findAll();

	public abstract MotelRoom updateMotelRoom(MotelRoom motelRoom);

	public abstract MotelRoom createMotelRoom(MotelRoom motelRoom);

	public abstract Page<MotelRoom> findRoomByAccountIdPageable(Pageable pageable, Long accountId);

	public abstract Boolean deleteMotelRoom(List<Long> listMotelId, String deleteBy);

	public abstract MotelRoom findMotelRoomById(Long motelId, Long accountId);

	public abstract List<MotelRoom> findMotelRoomByAccountId(Long accountId);
	
	public abstract List<MotelRoom> findMotelRoomByAccountIdForEdit(Long accountId, Long postId);

	public abstract List<Long> searchMotelRoom(Long provinceId, Long districtId);

	public abstract List<Long> findMotelIdByListCriteriaId(List<Long> listCriteriaId, int count);

	public abstract List<Long> findMotelIdByAcreateAndPrice(List<Long> listMotelId, Long price, Long acreage,
			int count);
}
