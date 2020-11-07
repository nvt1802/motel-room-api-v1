package tainv13.app.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import tainv13.app.model.Province;

public abstract class ProvinceService {
	public abstract List<?> findAllProvince();
	
	public abstract Province findProvinceById(Long provinceId);
	
	public abstract Page<?> findAllPageable(Pageable pageable);

	public abstract Page<?> findAllByKind(String kind, Pageable pageable);
}
