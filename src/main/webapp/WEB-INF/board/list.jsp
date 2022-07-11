<%--
  Created by IntelliJ IDEA.
  User: alsrn
  Date: 2022-07-10
  Time: 오후 11:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script src="https://kit.fontawesome.com/052e9eaead.js" crossorigin="anonymous"></script>
    <title>게시판 목록</title>
</head>
<body>
<div>
    <a href="#">Home</a>
</div>
<div>
    <%
        String test = (String) request.getAttribute("test");
    %>
    <h3><%=test%></h3>
</div>
<div>
    <form method="get" name="searchForm" id="searchForm" action="">
        <label>등록일</label>
        <input type="date" name="searchCreatedDateFrom" id="searchCreatedDateFrom"/>
        <label> ~ </label>
        <input type="date" name="searchCreatedDateTo" id="searchCreatedDateTo"/>
        <select name="searchCategory" id="searchCategory">
            <option value="0">전체 카테고리</option>
            </option>
        </select>
        <input type="text" name="searchText" id="searchText" placeholder="검색어를 입력해 주세요. (제목+작성자+내용)"/>
        <button type="submit">검색</button>
    </form>
</div>

<div>
    <label>총 건</label>
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
    <tr>
        <td>카테고리</td>
        <td><i class="fas fa-paperclip"></i>
        </td>
        <td>
            <a href="#">
                제목 (댓글수)
            </a></td>
        <td>작성자
        </td>
        <td>조회수
        </td>
        <td>등록일시
        </td>
        <td>수정일시
        </td>
    </tr>
    </tbody>
</table>

<div>
    <button type="button"
            onclick="location.href=''">
        등록
    </button>
</div>
</body>
</html>
