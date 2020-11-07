package tainv13.app.dto;

import java.util.List;

public class PostDTOPageable {
	private List<PostDTO> listPostDtos;
	private Integer totalPages;
	private Integer pageSize;

	public PostDTOPageable() {
		super();
	}

	public PostDTOPageable(List<PostDTO> listPostDtos, Integer totalPages, Integer pageSize) {
		super();
		this.listPostDtos = listPostDtos;
		this.totalPages = totalPages;
		this.pageSize = pageSize;
	}

	public List<PostDTO> getListPostDtos() {
		return listPostDtos;
	}

	public void setListPostDtos(List<PostDTO> listPostDtos) {
		this.listPostDtos = listPostDtos;
	}

	public Integer getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
}
