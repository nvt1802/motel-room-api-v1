package tainv13.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import tainv13.app.model.District;

public interface DistrictRepository extends JpaRepository<District, Long> {

	@Query(nativeQuery = true, value = "SELECT * FROM district WHERE province_id = :provinceId")
	List<District> findAllDistrictByProvinceId(@Param("provinceId") long provinceId);

	@Query(nativeQuery = true, value = "SELECT * FROM district WHERE district_id = :districtId")
	District findAllDistrictByDistricId(@Param("districtId") long districtId);
}
