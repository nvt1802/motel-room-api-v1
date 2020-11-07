package tainv13.app.common;

public class PageCommom {
	private int page;
	private int pageSize;

	public PageCommom() {
		super();
	}

	public PageCommom(int page, int pageSize) {
		super();
		this.page = page;
		this.pageSize = pageSize;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
}
