package tainv13.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import tainv13.app.model.Ward;

public interface WardRepository extends JpaRepository<Ward, Long> {

	@Query(nativeQuery = true, value = "SELECT * FROM ward WHERE district_id = :districtId")
	public List<Ward> findWardByDistricId(@Param("districtId") Long districtId);
}
