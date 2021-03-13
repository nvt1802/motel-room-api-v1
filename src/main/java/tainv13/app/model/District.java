package tainv13.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "district")
public class District {
	@Id
	private long districtId;

	@Column(columnDefinition = "varchar(100)")
	private String districtName;

	@Column(columnDefinition = "varchar(20)")
	private String kind;

	private double latitute;
	private double longitude;

	@ManyToOne
	@JoinColumn(name = "province_id")
	@JsonIgnore
	private Province province;

	public District() {
		super();
	}

	public long getDistrictId() {
		return districtId;
	}

	public void setDistrictId(long districtId) {
		this.districtId = districtId;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public double getLatitute() {
		return latitute;
	}

	public void setLatitute(double latitute) {
		this.latitute = latitute;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public Province getProvince() {
		return province;
	}

	public void setProvince(Province province) {
		this.province = province;
	}

	public District(long districtId, String districtName, String kind, double latitute, double longitude,
			Province province) {
		super();
		this.districtId = districtId;
		this.districtName = districtName;
		this.kind = kind;
		this.latitute = latitute;
		this.longitude = longitude;
		this.province = province;
	}
}
