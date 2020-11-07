package tainv13.app.dto;

public class UploadResponse {
	private String fileName;
	private Long fileSize;
	private String fileDownloadUri;

	public UploadResponse(String fileName, Long fileSize, String fileDownloadUri) {
		super();
		this.fileName = fileName;
		this.fileSize = fileSize;
		this.fileDownloadUri = fileDownloadUri;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Long getFileSize() {
		return fileSize;
	}

	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}

	public String getFileDownloadUri() {
		return fileDownloadUri;
	}

	public void setFileDownloadUri(String fileDownloadUri) {
		this.fileDownloadUri = fileDownloadUri;
	}

}
