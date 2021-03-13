package tainv13.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "ward")
public class Ward {
	@Id
	private long wardId;

	@Column(columnDefinition = "varchar(100)")
	private String wardName;

	@Column(columnDefinition = "varchar(20)")
	private String kind;

	@ManyToOne
	@JoinColumn(name = "district_id")
	@JsonIgnore
	private District district;

	private double latitute;
	private double longitude;

	public Ward() {
		super();
	}

	public long getWardId() {
		return wardId;
	}

	public void setWardId(long wardId) {
		this.wardId = wardId;
	}

	public String getWardName() {
		return wardName;
	}

	public void setWardName(String wardName) {
		this.wardName = wardName;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public District getDistrict() {
		return district;
	}

	public void setDistrict(District district) {
		this.district = district;
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

	public Ward(long wardId, String wardName, String kind, District district, double latitute, double longitude) {
		super();
		this.wardId = wardId;
		this.wardName = wardName;
		this.kind = kind;
		this.district = district;
		this.latitute = latitute;
		this.longitude = longitude;
	}
}
