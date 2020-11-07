package tainv13.app.common;

public class PageCommon {
	private int page;
	private int pageSize;

	public PageCommon() {
		super();
	}

	public PageCommon(int page, int pageSize) {
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
