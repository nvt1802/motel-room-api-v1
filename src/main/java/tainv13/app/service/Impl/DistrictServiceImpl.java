package tainv13.app.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tainv13.app.model.District;
import tainv13.app.repository.DistrictRepository;
import tainv13.app.service.DistrictService;

@Service
public class DistrictServiceImpl extends DistrictService {

	@Autowired
	private DistrictRepository districtRepository;

	@Override
	public List<?> findAllDistrict() {
		return districtRepository.findAll();
	}

	@Override
	public List<?> findAllDistrictByProvinceId(long provinceId) {
		return districtRepository.findAllDistrictByProvinceId(provinceId);
	}

	@Override
	public District findAllDistrictByDistricId(long districtId) {
		return districtRepository.findAllDistrictByDistricId(districtId);
	}

}
