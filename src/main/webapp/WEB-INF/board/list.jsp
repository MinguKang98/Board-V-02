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

    int prevPage = (search.getCurPage() - 10 < 0) ? 1 : search.getCurPage() - 10;
    int nextPage = (search.getCurPage() + 10 > search.getTotalPageCount()) ?
            search.getTotalPageCount() : search.getCurPage() + 10;

    String searchCategoryId = ("".equals(search.getCategoryId()))? "0" : search.getCategoryId();
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
            <option value="0" selected>전체 카테고리</option>
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
            <a href="view.jsp?boardId=<%=board.getBoardId()%>&curPage=<%=search.getCurPage()%>&searchCreatedDateFrom=<%=search.getCreatedDateFrom()%>&searchCreatedDateTo=<%=search.getCreatedDateTo()%>&searchCategoryId=<%=search.getCategoryId()%>&searchText=<%=search.getText()%>">
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
        <li><a href="list.jsp?curPage=1&searchCreatedDateFrom=<%=search.getCreatedDateFrom()%>&searchCreatedDateTo=<%=search.getCreatedDateTo()%>&searchCategoryId=<%=search.getCategoryId()%>&searchText=<%=search.getText()%>">
            <i class="fas fa-chevron-left"></i><i class="fas fa-chevron-left"></i>
        </a></li>
        <li><a href="list.jsp?curPage=<%=prevPage%>&searchCreatedDateFrom=<%=search.getCreatedDateFrom()%>&searchCreatedDateTo=<%=search.getCreatedDateTo()%>&searchCategoryId=<%=search.getCategoryId()%>&searchText=<%=search.getText()%>"><i class="fas fa-chevron-left"></i></a></li>
<%
    for (int i = search.getFirstPage(); i <= search.getLastPage(); i++) {
        if (i == search.getCurPage()) {
%>
        <li><a href="#"><%=i%></a></li>
<%
        }else{
%>
        <li><a href="list.jsp?curPage=<%=i%>&searchCreatedDateFrom=<%=search.getCreatedDateFrom()%>&searchCreatedDateTo=<%=search.getCreatedDateTo()%>&searchCategoryId=<%=search.getCategoryId()%>&searchText=<%=search.getText()%>"><%=i%></a></li>
<%
        }
    }
%>
        <li><a href="list.jsp?curPage=<%=nextPage%>&searchCreatedDateFrom=<%=search.getCreatedDateFrom()%>&searchCreatedDateTo=<%=search.getCreatedDateTo()%>&searchCategoryId=<%=search.getCategoryId()%>&searchText=<%=search.getText()%>"><i class="fas fa-chevron-right"></i></a></li>
        <li><a href="list.jsp?curPage=<%=search.getTotalPageCount()%>&searchCreatedDateFrom=<%=search.getCreatedDateFrom()%>&searchCreatedDateTo=<%=search.getCreatedDateTo()%>&searchCategoryId=<%=search.getCategoryId()%>&searchText=<%=search.getText()%>">
            <i class="fas fa-chevron-right"></i><i class="fas fa-chevron-right"></i>
        </a></li>
    </ul>
</nav>

<div>
    <button type="button" onclick="location.href='write.jsp'">
        등록
    </button>
</div>

<script>
    function getDate() {
        var today = new Date();
        var year = today.getFullYear();
        var month = ('0' + (today.getMonth() + 1)).slice(-2);
        var day = ('0' + today.getDate()).slice(-2);
        return year + '-' + month + '-' + day;
    }

    var searchCreatedDateFrom = document.getElementById("searchCreatedDateFrom");
    var searchCreatedDateTo = document.getElementById("searchCreatedDateTo");
    var searchCategory = document.getElementById("searchCategoryId");
    var searchText = document.getElementById("searchText");

    // 등록일 값이 없을때 오늘 반환
    if (<%="".equals(search.getCreatedDateFrom())%>) {
        searchCreatedDateFrom.value = getDate();
    } else {
        searchCreatedDateFrom.value = "<%=search.getCreatedDateFrom()%>";
    }

    if (<%="".equals(search.getCreatedDateTo())%>) {
        searchCreatedDateTo.value = getDate();
    } else {
        searchCreatedDateTo.value = "<%=search.getCreatedDateTo()%>";
    }

    // 값이 있으면 카테고리 value 반환
    searchCategory.options[<%=searchCategoryId%>].selected = "selected";

    // 값이 있으면 text value 반환
    if (!<%="".equals(search.getText())%>) {
        searchText.value = "<%=search.getText()%>";
    }

</script>

</body>
</html>
