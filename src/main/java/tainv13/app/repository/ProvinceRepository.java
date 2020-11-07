package tainv13.app.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import tainv13.app.model.Province;

public interface ProvinceRepository extends JpaRepository<Province, Long> {
	Page<?> findAllByKind(String kind, Pageable pageable);

	@Query(nativeQuery = true, value = "SELECT * FROM province WHERE province_id = :provinceId")
	Province findByProvinceId(@Param("provinceId") Long provinceId);
}
