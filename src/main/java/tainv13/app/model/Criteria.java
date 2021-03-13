package tainv13.app.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "criteria")
public class Criteria {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long criteriaId;

	@Column(columnDefinition = "varchar(100)")
	@NotBlank
	@Length(max = 100)
	private String criteriaName;

	@JsonIgnore
	@ManyToMany(mappedBy = "criteria", cascade = CascadeType.ALL)
	private List<MotelRoom> motelRoom;

	public Criteria() {
		super();
	}

	public Criteria(long criteriaId, String criteriaName, List<MotelRoom> motelRoom) {
		super();
		this.criteriaId = criteriaId;
		this.criteriaName = criteriaName;
		this.motelRoom = motelRoom;
	}

	public long getCriteriaId() {
		return criteriaId;
	}

	public void setCriteriaId(long criteriaId) {
		this.criteriaId = criteriaId;
	}

	public String getCriteriaName() {
		return criteriaName;
	}

	public void setCriteriaName(String criteriaName) {
		this.criteriaName = criteriaName;
	}

	public List<MotelRoom> getMotelRoom() {
		return motelRoom;
	}

	public void setMotelRoom(List<MotelRoom> motelRoom) {
		this.motelRoom = motelRoom;
	}
}
