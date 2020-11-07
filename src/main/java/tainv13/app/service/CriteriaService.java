package tainv13.app.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import tainv13.app.model.Criteria;

public abstract class CriteriaService {
	public abstract List<?> findAllCriteria();

	public abstract Page<Criteria> findAllCriteria(Pageable pageable);

	public abstract Criteria createCriteria(Criteria criteria);

	public abstract Criteria updateCriteria(Criteria criteria);

	public abstract Boolean deleteCriteria(List<Long> listCriteriaId);

	public abstract List<Criteria> findCriteriaByListCriteriaId(List<Long> listCriteriaId);
}
