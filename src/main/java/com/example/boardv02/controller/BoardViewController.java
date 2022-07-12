package com.example.boardv02.controller;

import com.example.boardv02.dao.BoardDAO;
import com.example.boardv02.dao.CategoryDAO;
import com.example.boardv02.dao.CommentDAO;
import com.example.boardv02.vo.BoardVO;
import com.example.boardv02.vo.CategoryVO;
import com.example.boardv02.vo.CommentVO;
import com.example.boardv02.vo.SearchCriteriaPaging;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class BoardViewController implements Controller {

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BoardDAO boardDAO = new BoardDAO();
        CategoryDAO categoryDAO = new CategoryDAO();
        CommentDAO commentDAO = new CommentDAO();

        SearchCriteriaPaging search = new SearchCriteriaPaging();
        search.setCreatedDateFrom(request.getParameter("searchCreatedDateFrom"));
        search.setCreatedDateTo(request.getParameter("searchCreatedDateTo"));
        search.setCategoryId(request.getParameter("searchCategoryId"));
        search.setText(request.getParameter("searchText"));
        if (request.getParameter("curPage") != null) {
            search.setCurPage(Integer.parseInt(request.getParameter("curPage")));
        }

        int boardId = Integer.parseInt(request.getParameter("boardId"));
        BoardVO board = boardDAO.getBoardById(boardId);
        CategoryVO category = categoryDAO.getCategoryById(board.getCategoryId());
        List<CommentVO> commentList = commentDAO.getCommentListByBoardId(boardId);
        boardDAO.updateVisitCount(boardId);

        request.setAttribute("searchCriteria", search);
        request.setAttribute("board", board);
        request.setAttribute("category", category);
        request.setAttribute("commentList", commentList);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/board/view.jsp");
        dispatcher.forward(request, response);
    }
}
