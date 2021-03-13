package tainv13.app.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "account")
public class Account extends ModelCommon {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long accountId;
	@NotBlank
	@Length(max = 50)
	private String userName;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "role")
	private Long role;
	
	private boolean accountStatus;

	@Column(name = "name", columnDefinition = "varchar(150)")
	@NotBlank
	@Length(max = 150)
	private String name;

	private Long gender;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date birthday;

	@NotBlank
	@Length(max = 11)
	private String phone;
	@NotBlank
	@Length(max = 50)
	private String email;
	private Long provinceId;
	private Long districtId;

	public Account() {
		super();
	}

	public Account(Long accountId, String userName, String password, Long role, boolean accountStatus, String name,
			Long gender, Date birthday, String phone, String email, Long provinceId, Long districtId) {
		super();
		this.accountId = accountId;
		this.userName = userName;
		this.password = password;
		this.role = role;
		this.accountStatus = accountStatus;
		this.name = name;
		this.gender = gender;
		this.birthday = birthday;
		this.phone = phone;
		this.email = email;
		this.provinceId = provinceId;
		this.districtId = districtId;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getRole() {
		return role;
	}

	public void setRole(Long role) {
		this.role = role;
	}

	public boolean getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(boolean accountStatus) {
		this.accountStatus = accountStatus;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getGender() {
		return gender;
	}

	public void setGender(Long gender) {
		this.gender = gender;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Long provinceId) {
		this.provinceId = provinceId;
	}

	public Long getDistrictId() {
		return districtId;
	}

	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}
}
