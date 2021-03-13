package tainv13.app.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "images")
public class Image {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long imageId;
	private String url;

	@ManyToOne
	@JoinColumn(name = "motel_id")
	private MotelRoom motelRoom;

	public Image() {
		super();
	}

	public Image(long imageId, String url, MotelRoom motelRoom) {
		super();
		this.imageId = imageId;
		this.url = url;
		this.motelRoom = motelRoom;
	}

	public long getImageId() {
		return imageId;
	}

	public void setImageId(long imageId) {
		this.imageId = imageId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@JsonIgnore
	public MotelRoom getMotelRoom() {
		return motelRoom;
	}

	public void setMotelRoom(MotelRoom motelRoom) {
		this.motelRoom = motelRoom;
	}
}
