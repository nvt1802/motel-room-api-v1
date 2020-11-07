package tainv13.app.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import tainv13.app.model.Criteria;
import tainv13.app.repository.CriteriaRepository;
import tainv13.app.service.CriteriaService;

@Service
public class CriteriaServiceImpl extends CriteriaService {

	@Autowired
	private CriteriaRepository criteriaRepository;

	@Override
	public List<?> findAllCriteria() {
		return criteriaRepository.findAll();
	}

	@Override
	public Page<Criteria> findAllCriteria(Pageable pageable) {
		return criteriaRepository.findAll(pageable);
	}

	@Override
	public Criteria updateCriteria(Criteria criteria) {
		try {
			return criteriaRepository.save(criteria);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Boolean deleteCriteria(List<Long> listCriteriaId) {
		try {
			for (Long id : listCriteriaId) {
				Criteria criteria = criteriaRepository.getOne(id);
				criteriaRepository.delete(criteria);
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Criteria createCriteria(Criteria criteria) {
		try {
			return criteriaRepository.save(criteria);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<Criteria> findCriteriaByListCriteriaId(List<Long> listCriteriaId) {
		return criteriaRepository.findAllById(listCriteriaId);
	}
}
