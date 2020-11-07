package tainv13.app.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import tainv13.app.model.Province;
import tainv13.app.repository.ProvinceRepository;
import tainv13.app.service.ProvinceService;

@Service
public class ProvinceServiceImpl extends ProvinceService {

	@Autowired
	private ProvinceRepository provinceRepository;

	@Override
	public List<?> findAllProvince() {
		return provinceRepository.findAll();
	}

	@Override
	public Page<?> findAllPageable(Pageable pageable) {
		return provinceRepository.findAll(pageable);
	}

	@Override
	public Page<?> findAllByKind(String kind, Pageable pageable) {
		return provinceRepository.findAllByKind(kind, pageable);
	}

	@Override
	public Province findProvinceById(Long provinceId) {
		return provinceRepository.findByProvinceId(provinceId);
	}
}
