package tainv13.app.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import tainv13.app.model.Account;

public class AccountDTO extends CommonDTO {
	private Long accountId;
	private String userName;
	private Long role;
	private boolean accountStatus;
	private String name;
	private Long gender;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date birthday;
	
	private String phone;
	private String email;
	private Long provinceId;
	private Long districtId;

	public AccountDTO() {
		super();
	}

	public AccountDTO(Date createAt, Date updateAt, Date deleteAt, String createBy, String updateBy, String deleteBy) {
		super(createAt, updateAt, deleteAt, createBy, updateBy, deleteBy);
	}

	public AccountDTO(Account account) {
		super(account.getCreateAt(), account.getUpdateAt(), account.getDeleteAt(), account.getCreateBy(),
				account.getUpdateBy(), account.getDeleteBy());
		this.accountId = account.getAccountId();
		this.userName = account.getUserName();
		this.role = account.getRole();
		this.accountStatus = account.getAccountStatus();
		this.name = account.getName();
		this.gender = account.getGender();
		this.birthday = account.getBirthday();
		this.phone = account.getPhone();
		this.email = account.getEmail();
		this.provinceId = account.getProvinceId();
		this.districtId = account.getDistrictId();
	}

	public AccountDTO(Long accountId, String userName, Long role, boolean accountStatus, String name, Long gender,
			Date birthday, String phone, String email, Long provinceId, Long districtId) {
		super();
		this.accountId = accountId;
		this.userName = userName;
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

	public AccountDTO(Date createAt, Date updateAt, Date deleteAt, String createBy, String updateBy, String deleteBy,
			Long accountId, String userName, Long role, boolean accountStatus, String name, Long gender, Date birthday,
			String phone, String email, Long provinceId, Long districtId) {
		super(createAt, updateAt, deleteAt, createBy, updateBy, deleteBy);
		this.accountId = accountId;
		this.userName = userName;
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
