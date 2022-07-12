<%@ page import="com.example.boardv02.vo.SearchCriteriaPaging" %>
<%@ page import="com.example.boardv02.vo.BoardVO" %>
<%@ page import="com.example.boardv02.vo.CategoryVO" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.boardv02.vo.CommentVO" %><%--
  Created by IntelliJ IDEA.
  User: alsrn
  Date: 2022-07-12
  Time: 오전 12:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    SearchCriteriaPaging search = (SearchCriteriaPaging) request.getAttribute("searchCriteria");
    BoardVO board = (BoardVO) request.getAttribute("board");
    CategoryVO category = (CategoryVO) request.getAttribute("category");
    List<CommentVO> commentList = (List<CommentVO>) request.getAttribute("commentList");
%></>
<html>
<head>
    <script src="https://kit.fontawesome.com/052e9eaead.js" crossorigin="anonymous"></script>
    <title>게시글</title>
</head>
<body>
<!--게시글-->
<div>
    <!--제목-->
    <div>
        <div>
            <span><%=board.getUser()%></span>
            <span>등록일시 <%=board.getCreatedDate()%></span>
            <span>수정일시 <%=board.getUpdatedDate()%></span>
        </div>
    </div>
    <div>
        <h2>[<%=category.getName()%>]    <%=board.getTitle()%>
        </h2>
        <span>조회수: <%=board.getVisitCount()%></span>
    </div>
</div>

<!--본문-->
<div>
    <p><%=board.getContent()%>
    </p>
</div>

<%--첨부파일--%>
<div><i class="fas fa-download"></i>
    <a href="#"></a>
</div>

</div>

<!--댓글-->
<div>
<%
    for (CommentVO comment : commentList) {
%>
    <div>
        <div><%=comment.getCreatedDate()%>
        </div>
        <div><%=comment.getContent()%>
        </div>
    </div>
    <%
    }
%>
    <form method="post" name="commentForm" id="commentForm" action="commentSave?boardId=<%=board.getBoardId()%>">
        <input type="text" name="content" id="content" required placeholder="댓글을 입력해 주세요."/>
        <button type="submit">등록</button>
    </form>
</div>

<div>
    <button type="button" onclick="location.href='list.jsp'">목록</button>
    <button type="button" onclick="location.href='passwordCheck.jsp?boardId=<%=board.getBoardId()%>&type=modify'">수정</button>
    <button type="button" onclick="location.href='passwordCheck.jsp?boardId=<%=board.getBoardId()%>&type=delete'">삭제</button>
</div>
</body>
</html>
