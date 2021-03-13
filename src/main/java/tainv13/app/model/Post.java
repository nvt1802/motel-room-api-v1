package tainv13.app.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "post")
public class Post extends ModelCommon {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long postId;

	@Column(columnDefinition = "varchar(200)")
	@NotBlank
	@Length(max = 200)
	private String postTitle;

	private long postView;

	@CreationTimestamp
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private Date postDate;

	@ManyToOne
	@JoinColumn(name = "motel_id")
	private MotelRoom motelRoom;

	@Column(columnDefinition = "varchar(255)")
	@NotBlank
	@Length(max = 255)
	private String description;

	private boolean postStatus;

	@ManyToOne
	@JoinColumn(name = "account_id")
	private Account account;

	public Post() {
		super();
	}

	public Post(long postId, String postTitle, long postView, Date postDate, MotelRoom motelRoom, String description,
			boolean postStatus) {
		super();
		this.postId = postId;
		this.postTitle = postTitle;
		this.postView = postView;
		this.postDate = postDate;
		this.motelRoom = motelRoom;
		this.description = description;
		this.postStatus = postStatus;
	}

	public Post(long postId, String postTitle, long postView, Date postDate, MotelRoom motelRoom, String description,
			boolean postStatus, Account account) {
		super();
		this.postId = postId;
		this.postTitle = postTitle;
		this.postView = postView;
		this.postDate = postDate;
		this.motelRoom = motelRoom;
		this.description = description;
		this.postStatus = postStatus;
		this.account = account;
	}

	public long getPostId() {
		return postId;
	}

	public void setPostId(long postId) {
		this.postId = postId;
	}

	public String getPostTitle() {
		return postTitle;
	}

	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}

	public long getPostView() {
		return postView;
	}

	public void setPostView(long postView) {
		this.postView = postView;
	}

	public Date getPostDate() {
		return postDate;
	}

	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}

	public MotelRoom getMotelRoom() {
		return motelRoom;
	}

	public void setMotelRoom(MotelRoom motelRoom) {
		this.motelRoom = motelRoom;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isPostStatus() {
		return postStatus;
	}

	public void setPostStatus(boolean postStatus) {
		this.postStatus = postStatus;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

}
