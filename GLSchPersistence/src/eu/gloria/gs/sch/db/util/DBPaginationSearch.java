package eu.gloria.gs.sch.db.util;

public class DBPaginationSearch {

	private int pageNumber;
	private int pageSize;

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	public long getFirstPageRow(){
		long result = (pageNumber - 1) * pageSize;
		if (result < 0) result = 0;
		return result;
	}
	
	public int getFirstPageRowInt(){
		return (new Long(getFirstPageRow())).intValue();
	}

}
