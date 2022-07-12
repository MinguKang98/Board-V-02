package com.example.boardv02.controller;

import com.example.boardv02.dao.BoardDAO;
import com.example.boardv02.dao.CommentDAO;
import com.example.boardv02.vo.CommentVO;
import com.example.boardv02.vo.SearchCriteriaPaging;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CommentSaveController implements Controller{

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CommentDAO commentDAO = new CommentDAO();
        BoardDAO boardDAO = new BoardDAO();

        SearchCriteriaPaging search = new SearchCriteriaPaging();
        search.setCreatedDateFrom(request.getParameter("searchCreatedDateFrom"));
        search.setCreatedDateTo(request.getParameter("searchCreatedDateTo"));
        search.setCategoryId(request.getParameter("searchCategoryId"));
        search.setText(request.getParameter("searchText"));
        if (request.getParameter("curPage") != null) {
            search.setCurPage(Integer.parseInt(request.getParameter("curPage")));
        }

        CommentVO comment = new CommentVO();
        int boardId = Integer.parseInt(request.getParameter("boardId"));
        String content = request.getParameter("content");
        comment.setContent(content);
        comment.setBoardId(boardId);

        commentDAO.addComment(comment);
        boardDAO.updateCommentCount(boardId);
//        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/board/view.jsp?boardId="
//                + boardId);
//        dispatcher.forward(request, response);
        response.sendRedirect("view.jsp?boardId=" + boardId);
    }
}
