<%@ page import="java.util.List" %>
<%@ page import="com.example.boardv02.vo.CategoryVO" %>
<%@ page import="com.example.boardv02.vo.SearchCriteriaPaging" %><%--
  Created by IntelliJ IDEA.
  User: alsrn
  Date: 2022-07-12
  Time: 오후 4:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
//    SearchCriteriaPaging search = (SearchCriteriaPaging) request.getAttribute("searchCriteria");
    List<CategoryVO> categoryList = (List<CategoryVO>) request.getAttribute("categoryList");
%>
<html>
<head>
    <title>게시판 등록</title>
</head>
<body>
<form method="post" name="writeForm" id="writeForm" action="boardSave">
    <table>
        <tr>
            <th>카테고리</th>
            <td>
                <select name="category" id="category">
                    <option value="0" selected>카테고리 선택</option>
<%
    for (CategoryVO category : categoryList) {
%>
                    <option value="<%=category.getCategoryId()%>"><%=category.getName()%>
                    </option>
<%
    }
%>
                </select>
                <span id="categoryWarning"></span>
            </td>
        </tr>
        <tr>
            <th>작성자</th>
            <td>
                <input type="text" name="user" id="user" required minlength="3" maxlength="5"/>
                <span id="userWarning"></span>
            </td>
        </tr>
        <tr>
            <th>비밀번호</th>
            <td>
                <input type="password" name="password" id="password" placeholder="비밀번호" required
                       pattern="^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{4,15}$"/>
                <input type="password" name="passwordCheck" id="passwordCheck" placeholder="비밀번호 확인" required/>
                <span id="passwordWarning"></span>
            </td>
        </tr>
        <tr>
            <th>제목</th>
            <td>
                <input type="text" name="title" id="title" required minlength="4" maxlength="100"/>
                <span id="titleWarning"></span>
            </td>
        </tr>
        <tr>
            <th>내용</th>
            <td>
                <textarea name="content" id="content" required minlength="4" maxlength="2000"></textarea>
                <span id="contentWarning"></span>
            </td>
        </tr>
        <tr>
            <th>파일첨부</th>
            <td>
                <div>
                    <input type="file" name="file1" id="file1"/>
                </div>
                <div>
                    <input type="file" name="file2" id="file2"/>
                </div>
                <div>
                    <input type="file" name="file3" id="file3"/>
                </div>
            </td>
        </tr>
    </table>
    <button type="button" onclick="history.back()">취소</button>
    <button type="button" onclick="validCheck()">저장</button>
</form>

<script>
    function validCheck() {
        // 유효성 검사

        // 폼 제출
        document.writeForm.submit();

    }
</script>
</body>
</html>
