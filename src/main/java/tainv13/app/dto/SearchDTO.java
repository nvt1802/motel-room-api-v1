package tainv13.app.dto;

import java.util.List;

import tainv13.app.common.PageCommon;

public class SearchDTO {
	private long provinceId;
	private long districtId;
	private long price;
	private long acreage;
	private List<Long> listCriteriaId;
	private boolean optionAdvance;
	private PageCommon pageCommon;

	public SearchDTO() {
		super();
	}

	public SearchDTO(long provinceId, long districtId, long price, long acreage, List<Long> listCriteriaId,
			boolean optionAdvance, PageCommon pageCommon) {
		super();
		this.provinceId = provinceId;
		this.districtId = districtId;
		this.price = price;
		this.acreage = acreage;
		this.listCriteriaId = listCriteriaId;
		this.optionAdvance = optionAdvance;
		this.pageCommon = pageCommon;
	}

	public long getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(long provinceId) {
		this.provinceId = provinceId;
	}

	public long getDistrictId() {
		return districtId;
	}

	public void setDistrictId(long districtId) {
		this.districtId = districtId;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public long getAcreage() {
		return acreage;
	}

	public void setAcreage(long acreage) {
		this.acreage = acreage;
	}

	public List<Long> getListCriteriaId() {
		return listCriteriaId;
	}

	public void setListCriteriaId(List<Long> listCriteriaId) {
		this.listCriteriaId = listCriteriaId;
	}

	public boolean isOptionAdvance() {
		return optionAdvance;
	}

	public void setOptionAdvance(boolean optionAdvance) {
		this.optionAdvance = optionAdvance;
	}

	public PageCommon getPageCommon() {
		return pageCommon;
	}

	public void setPageCommon(PageCommon pageCommon) {
		this.pageCommon = pageCommon;
	}
}
