package com.example.boardv02.vo;

import java.util.HashMap;

public class SearchCriteriaPaging {
    // 입력 받음
    private int curPage = 1; // 현재 페이지 번호
    private int rowSizePerPage = 10; // 노출되는 게시글 갯수
    private int pageSize = 10; // 노출되는 페이지 수
    private int totalRowCount; // 총 게시글 갯수

    // 계산 해야함
    private int firstRow; // 노출된 첫 게시글
    private int lastRow; // 노출된 마지막 게시글
    private int totalPageCount; // 최종 페이지
    private int firstPage; // 현재 보이는 처음 페이지
    private int lastPage; // 현재 보이는 마지막 페이지

    // 검색 조건
    private String createdDateFrom; // 등록일
    private String createdDateTo; // 등록일
    private String categoryId; // 카테고리
    private String text; // 제목 or 작성자 or 텍스트


    public void pageSetting() {
        totalPageCount = (totalRowCount - 1) / rowSizePerPage + 1;

        firstRow = (curPage - 1) * rowSizePerPage;
        lastRow = firstRow + rowSizePerPage - 1;
        if (lastRow > totalRowCount) lastRow = totalRowCount - 1;

        firstPage = (curPage - 1) / pageSize * pageSize + 1;
        lastPage = firstPage + pageSize - 1;
        if (lastPage > totalPageCount) lastPage = totalPageCount;
    }

    public HashMap<String, Object> getSearchCriteriaMap() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("createdDateFrom", this.getCreatedDateFrom());
        map.put("createdDateTo", this.getCreatedDateTo());
        map.put("categoryId", this.getCategoryId());
        map.put("text", this.getText());
        return map;
    }

    public HashMap<String, Object> getPagingAndSearchCriteriaMap() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("firstRow", this.getFirstRow());
        map.put("rowSizePerPage", this.getRowSizePerPage());
        map.put("createdDateFrom", this.getCreatedDateFrom());
        map.put("createdDateTo", this.getCreatedDateTo());
        map.put("categoryId", this.getCategoryId());
        map.put("text", this.getText());
        return map;
    }


    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) { this.curPage = curPage; }

    public int getRowSizePerPage() {
        return rowSizePerPage;
    }

    public void setRowSizePerPage(int rowSizePerPage) {
        this.rowSizePerPage = rowSizePerPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalRowCount() {
        return totalRowCount;
    }

    public void setTotalRowCount(int totalRowCount) {
        this.totalRowCount = totalRowCount;
    }

    public int getFirstRow() {
        return firstRow;
    }

    public void setFirstRow(int firstRow) {
        this.firstRow = firstRow;
    }

    public int getLastRow() {
        return lastRow;
    }

    public void setLastRow(int lastRow) {
        this.lastRow = lastRow;
    }

    public int getTotalPageCount() {
        return totalPageCount;
    }

    public void setTotalPageCount(int totalPageCount) {
        this.totalPageCount = totalPageCount;
    }

    public int getFirstPage() {
        return firstPage;
    }

    public void setFirstPage(int firstPage) {
        this.firstPage = firstPage;
    }

    public int getLastPage() {
        return lastPage;
    }

    public void setLastPage(int lastPage) {
        this.lastPage = lastPage;
    }

    public String getCreatedDateFrom() {
        return createdDateFrom;
    }

    public void setCreatedDateFrom(String createdDateFrom) {
        if (createdDateFrom == null) {
            this.createdDateFrom = "";
        } else {
            this.createdDateFrom = createdDateFrom;
        }
    }

    public String getCreatedDateTo() {
        return createdDateTo;
    }

    public void setCreatedDateTo(String createdDateTo) {
        if (createdDateTo == null) {
            this.createdDateTo = "";
        } else {
            this.createdDateTo = createdDateTo;
        }
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        if (categoryId == null) {
            this.categoryId = "";
        } else {
            this.categoryId = categoryId;
        }
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        if (text == null) {
            this.text = "";
        } else {
            this.text = text;
        }
    }

    public static void main(String[] args) {
        SearchCriteriaPaging paging= new SearchCriteriaPaging();
        paging.setCreatedDateFrom(null);
        boolean test = ((paging.getCreatedDateFrom()) == null);
        System.out.println("test = " + test);
    }
}
