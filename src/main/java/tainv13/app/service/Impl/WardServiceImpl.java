package tainv13.app.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tainv13.app.model.Ward;
import tainv13.app.repository.WardRepository;
import tainv13.app.service.WardService;

@Service
public class WardServiceImpl extends WardService {

	@Autowired
	private WardRepository wardRepository;

	@Override
	public List<?> findAllWard() {
		return wardRepository.findAll();
	}

	@Override
	public List<Ward> findWardByDistricId(Long districtId) {
		return wardRepository.findWardByDistricId(districtId);
	}
}
