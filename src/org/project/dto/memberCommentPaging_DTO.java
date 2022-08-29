package org.project.dto;

public class memberCommentPaging_DTO {

	private int page=1;//현재 페이지수 (get)
	private int totalCount;//전체 row 수 (get)
	private int beginPage; // 출력 시작
	private int endPage;// 출력 끝
	private int displayRow = 3;//한페이지에 몇개의 row가 보일지 (선택 set)
	private int displayPage = 3;//한페이지에 몇개의 페이지 (선택 set)
	boolean prev; //prev 버튼이 보일건지 안보일건지
	boolean next; //next 버튼이 보일건지 안보일건지
	
	
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		//setTotalCount()를 꼭 호출해야 paging이 되기 때문에
		//setTotalCount()를 호출하면 Paging_DTO가  자동으로 호출되게 한다. 
		Paging_DTO();
	}

	public int getBeginPage() {
		return beginPage;
	}
	public void setBeginPage(int beginPage) {
		this.beginPage = beginPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public int getDisplayRow() {
		return displayRow;
	}
	public void setDisplayRow(int displayRow) {
		this.displayRow = displayRow;
	}
	public int getDisplayPage() {
		return displayPage;
	}
	public void setDisplayPage(int displayPage) {
		this.displayPage = displayPage;
	}
	public boolean isPrev() {
		return prev;
	}
	public void setPrev(boolean prev) {
		this.prev = prev;
	}
	public boolean isNext() {
		return next;
	}
	public void setNext(boolean next) {
		this.next = next;
	}
	private void Paging_DTO() {
		// prev, next, beginPage, endPage를 계산해서 만든다.
		//2+9 = 11, 11/10=1, 1*10 = 10
		//10+9 = 19, 19/10=1, 1*10 = 10
		//11+9 = 20, 20/10=2, 2*10 = 20
		//20+9 = 29, 29/10=2, 2*10 = 20
		//111+9 = 120, 120/10=12, 12*10 = 120
		
		//(2+9)/10*10(1번 방법)
		//endPage = ((page+(displayPage-1))/displayPage)*displayPage;
		
		//1/10 01(올림) 1 (2번 방법)
		endPage = ((int)Math.ceil(page/(double)displayPage))*displayPage;
		System.out.println("endPage : "+endPage);
		
		beginPage = endPage-(displayPage-1);
		System.out.println("beginPage : "+beginPage);
		
		// 글 갯수 ex)32
		//32/10 = 3.2(올림) 4페이지
		//2=? 2/10
		
		int totalPage = (int)Math.ceil(totalCount/(double)displayRow);
		
		if(totalPage<endPage) {
			endPage = totalPage;
			next = false;
		}else {
			next = true;
		}
		
		prev = (beginPage==1)?false:true;//page가 11이상에만 나온다.
		System.out.println("endPage : " + endPage);
		System.out.println("totalPage : "+totalPage);
		
	}
}
