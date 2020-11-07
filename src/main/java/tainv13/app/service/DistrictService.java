package tainv13.app.service;

import java.util.List;

import tainv13.app.model.District;

public abstract class DistrictService {
	public abstract List<?> findAllDistrict();
	
	public abstract List<?> findAllDistrictByProvinceId(long provinceId);
	
	public abstract District findAllDistrictByDistricId(long districtId);
}
