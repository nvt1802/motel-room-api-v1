package tainv13.app.service;

import java.util.List;

import tainv13.app.model.Ward;

public abstract class WardService {
	public abstract List<?> findAllWard();
	
	public abstract List<Ward> findWardByDistricId(Long districtId);
}
