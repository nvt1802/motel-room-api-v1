package tainv13.app.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import tainv13.app.model.MotelRoom;

public interface MotelRoomRepository extends JpaRepository<MotelRoom, Long> {

	String QUERY_SEARCH = "SELECT DISTINCT motel_id FROM motel_room WHERE delete_at is null and (province_id = :provinceId or -1 = :provinceId) and (district_id = :districtId or -1 = :districtId)";

	@Query(nativeQuery = true, value = "SELECT * FROM motel_room WHERE account_id = :accountId AND delete_at IS NULL")
	Page<MotelRoom> findRoomByAccountIDAndDeleteAtIsNull(Pageable pageable, @Param("accountId") Long accountId);

	@Query(nativeQuery = true, value = "SELECT * FROM motel_room WHERE motel_id = :motelId AND  account_id = :accountId AND delete_at IS NULL")
	MotelRoom findMotelRoomById(@Param("motelId") Long motelId, @Param("accountId") Long accountId);

	@Query(nativeQuery = true, value = "SELECT * FROM motel_room WHERE account_id = :accountId AND delete_at IS NULL and motel_id not in ( SELECT motel_id from post WHERE delete_at is null)")
	List<MotelRoom> findMotelRoomByAccountId(@Param("accountId") Long accountId);

	@Query(nativeQuery = true, value = "SELECT * FROM motel_room WHERE account_id = :accountId AND delete_at IS NULL and motel_id not in ( SELECT motel_id from post WHERE post_id <> :postId  and delete_at is null)")
	List<MotelRoom> findMotelRoomByAccountIdForEdit(@Param("accountId") Long accountId, @Param("postId") Long postId);

	@Query(nativeQuery = true, value = QUERY_SEARCH)
	public List<Long> searchMotelRoom(@Param("provinceId") Long provinceId, @Param("districtId") Long districtId);

	@Query(nativeQuery = true, value = "SELECT motel_id FROM motel_criteria WHERE criteria_id in :listCriteriaId GROUP BY motel_id Having COUNT(*) = :count")
	public List<Long> findMotelIdByListCriteriaId(@Param("listCriteriaId") List<Long> listCriteriaId,
			@Param("count") int count);

	@Query(nativeQuery = true, value = "SELECT motel_id FROM motel_room WHERE delete_at is null and price <= :price and acreage <= :acreage and (motel_id in :listMotelId or 0 = :count)")
	public List<Long> findMotelIdByAcreateAndPrice(@Param("listMotelId") List<Long> listMotelId,
			@Param("price") Long price, @Param("acreage") Long acreage, @Param("count") int count);

}
