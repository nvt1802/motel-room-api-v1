package tainv13.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "province")
public class Province {
	@Id
	private long provinceId;

	@Column(columnDefinition = "varchar(100)")
	private String provinceName;
	
	@Column(columnDefinition = "varchar(15)")
	private String kind;

	public Province() {
		super();
	}

	public Province(long provinceId, String provinceName, String kind) {
		super();
		this.provinceId = provinceId;
		this.provinceName = provinceName;
		this.kind = kind;
	}

	public long getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(long provinceId) {
		this.provinceId = provinceId;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	
	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}
}
