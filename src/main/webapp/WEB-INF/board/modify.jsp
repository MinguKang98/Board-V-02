<%--
  Created by IntelliJ IDEA.
  User: alsrn
  Date: 2022-07-12
  Time: 오후 10:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.boardv02.vo.BoardVO" %>
<%@ page import="com.example.boardv02.vo.CategoryVO" %>

<%
    BoardVO board = (BoardVO) request.getAttribute("board");
    CategoryVO category = (CategoryVO) request.getAttribute("category");
%>
<html>
<head>
    <title>게시판 수정</title>
</head>
<body>
<form method="post" name="modifyForm" id="modifyForm"
      action="#">
    <table>
        <tr>
            <th>카테고리</th>
            <td>
                <span name="category" id="category"><%=category.getName()%></span>
            </td>
        </tr>
        <tr>
            <th>등록 일시</th>
            <td>
                <span name="createdDate" id="createdDate"><%=board.getCreatedDate()%></span>
            </td>
        </tr>
        <tr>
            <th>수정 일시</th>
            <td>
                <span name="updatedDate" id="updatedDate"><%=board.getUpdatedDate()%></span>
            </td>
        </tr>
        <tr>
            <th>조회수</th>
            <td>
                <span name="count" id="count"><%=board.getVisitCount()%></span>
            </td>
        </tr>
        <tr>
            <th>작성자</th>
            <td>
                <input type="text" name="user" id="user" value="<%=board.getUser()%>" required
                       minlength="3" maxlength="5"/>
                <span id="userWarning"></span>
        </tr>
        <tr>
            <th>비밀번호</th>
            <td>
                <input type="password" name="password" id="password" placeholder="비밀번호" required
                       pattern="^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{4,15}$"/>
                <span id="passwordWarning"></span>
            </td>
        </tr>
        <tr>
            <th>제목</th>
            <td>
                <input type="text" name="title" id="title" value="<%=board.getTitle()%>" required
                       minlength="4" maxlength="100"/>
                <span id="titleWarning"></span>
            </td>
        </tr>
        <tr>
            <th>내용</th>
            <td>
                <textarea name="content" id="content" required minlength="4" maxlength="2000"><%=board.getContent()%></textarea>
                <span id="contentWarning"></span>
            </td>
        </tr>
        <tr>
            <th>파일첨부</th>
            <td>
<%
    for (int i = 1; i <= 3; i++) {
%>
<%--                <div>--%>
<%--                    <i class="fas fa-paperclip"></i><span>다운로드</span>--%>
<%--                    <button type="button" onclick="location.href='#'">--%>
<%--                        Download</button>--%>
<%--                    <button type="button" id="deleteButton" onclick="deleteFile(this.id)">X</button>--%>
<%--                </div>--%>
<%
%>
    <div>
        <input type="file" />
    </div>
<%
    }
%>
            </td>
        </tr>
    </table>
    <button type="button" onclick="location.href='view.jsp?boardId=<%=board.getBoardId()%>'">취소</button>
    <button type="button" onclick="validCheck()">저장</button>
</form>

</body>
</html>
