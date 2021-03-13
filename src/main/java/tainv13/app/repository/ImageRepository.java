package tainv13.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import tainv13.app.model.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {

	@Query(nativeQuery = true, value = "SELECT * FROM images WHERE url LIKE :url and motel_id = :motelId")
	public List<Image> getImageByUrl(@Param("url") String url, @Param("motelId") Long motelId);
	
	@Query(nativeQuery = true, value = "SELECT * FROM images WHERE motel_id = :motelId")
	public List<Image> findImageByMotelId(@Param("motelId") Long motelId);
}
