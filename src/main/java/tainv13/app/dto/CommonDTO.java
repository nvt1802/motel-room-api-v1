package tainv13.app.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class CommonDTO {
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createAt;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updateAt;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private Date deleteAt;
	private String createBy;
	private String updateBy;
	private String deleteBy;

	public CommonDTO() {
		super();
	}

	public CommonDTO(Date createAt, Date updateAt, Date deleteAt, String createBy, String updateBy, String deleteBy) {
		super();
		this.createAt = createAt;
		this.updateAt = updateAt;
		this.deleteAt = deleteAt;
		this.createBy = createBy;
		this.updateBy = updateBy;
		this.deleteBy = deleteBy;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public Date getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(Date updateAt) {
		this.updateAt = updateAt;
	}

	public Date getDeleteAt() {
		return deleteAt;
	}

	public void setDeleteAt(Date deleteAt) {
		this.deleteAt = deleteAt;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public String getDeleteBy() {
		return deleteBy;
	}

	public void setDeleteBy(String deleteBy) {
		this.deleteBy = deleteBy;
	}
}
