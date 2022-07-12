<%--
  Created by IntelliJ IDEA.
  User: alsrn
  Date: 2022-07-10
  Time: 오후 11:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.boardv02.vo.BoardVO" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.boardv02.vo.SearchCriteriaPaging" %>
<%@ page import="com.example.boardv02.vo.CategoryVO" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>

<%
    List<CategoryVO> categoryList = (List<CategoryVO>) request.getAttribute("categoryList");
    Map<Integer, String> categoryMap = new HashMap<>();

    List<BoardVO> boardList = (List<BoardVO>) request.getAttribute("boardList");
    int totalCount = (int) request.getAttribute("totalCount");
    SearchCriteriaPaging search = (SearchCriteriaPaging) request.getAttribute("searchCriteria");

    int prevPage = (search.getCurPage() - 1 < search.getFirstPage()) ?
            search.getFirstPage() : search.getCurPage() - 1;
    int nextPage = (search.getCurPage() + 1 > search.getTotalPageCount()) ?
            search.getTotalPageCount() : search.getCurPage() + 1;
%>

<html>
<head>
    <script src="https://kit.fontawesome.com/052e9eaead.js" crossorigin="anonymous"></script>
    <title>게시판 목록</title>
</head>
<body>
<div>
    <a href="list.jsp">Home</a>
</div>
<div>

</div>
<div>
    <form method="get" name="searchForm" id="searchForm" action="list.jsp">
        <label>등록일</label>
        <input type="date" name="searchCreatedDateFrom" id="searchCreatedDateFrom"/>
        <label> ~ </label>
        <input type="date" name="searchCreatedDateTo" id="searchCreatedDateTo"/>
        <select name="searchCategoryId" id="searchCategoryId">
            <option value="0">전체 카테고리</option>
<%
    for (CategoryVO category : categoryList) {
%>
            <option value="<%=category.getCategoryId()%>"><%=category.getName()%></option>
<%
        categoryMap.put(category.getCategoryId(), category.getName());
    }
%>
        </select>
        <input type="text" name="searchText" id="searchText" placeholder="검색어를 입력해 주세요. (제목+작성자+내용)"/>
        <button type="submit">검색</button>
    </form>
</div>

<div>
    <label>총 <%=totalCount%>건</label>
</div>
<table>
    <thead>
    <tr>
        <th>카테고리</th>
        <th></th>
        <th>제목</th>
        <th>작성자</th>
        <th>조회수</th>
        <th>등록 일시</th>
        <th>수정 일시</th>
    </tr>
    </thead>
    <tbody>
<%
    for (BoardVO board : boardList) {

%>
    <tr>
        <td><%=categoryMap.get(board.getCategoryId())%></td>
        <td><%=(board.isFileExist()) ? "<i class=\"fas fa-paperclip\"></i>\n" : " "%>
        </td>
        <td>
            <a href="view.jsp?boardId=<%=board.getBoardId()%>">
                <%=board.getTitle()%> (<%=board.getCommentCount()%>)
            </a></td>
        <td><%=board.getTitle()%>
        </td>
        <td><%=board.getVisitCount()%>
        </td>
        <td><%=board.getCreatedDate()%>
        </td>
        <td><%=board.getUpdatedDate()%>
        </td>
    </tr>
<%
    }
%>
    </tbody>
</table>

<nav>
    <ul>
        <li><a href="list.jsp?curPage=1">
            <i class="fas fa-chevron-left"></i><i class="fas fa-chevron-left"></i>
        </a></li>
        <li><a href="list.jsp?curPage=<%=prevPage%>"><i class="fas fa-chevron-left"></i></a></li>
<%
    for (int i = search.getFirstPage(); i <= search.getLastPage(); i++) {
        if (i == search.getCurPage()) {
%>
        <li><a href="#"><%=i%></a></li>
<%
        }else{
%>
        <li><a href="list.jsp?curPage=<%=i%>"><%=i%></a></li>
<%
        }
    }
%>
        <li><a href="list.jsp?curPage=<%=nextPage%>"><i class="fas fa-chevron-right"></i></a></li>
        <li><a href="list.jsp?curPage=<%=search.getTotalPageCount()%>">
            <i class="fas fa-chevron-right"></i><i class="fas fa-chevron-right"></i>
        </a></li>
    </ul>
</nav>

<div>
    <button type="button" onclick="location.href='write.jsp'">
        등록
    </button>
</div>
</body>
</html>
