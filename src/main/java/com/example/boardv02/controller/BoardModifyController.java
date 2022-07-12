package com.example.boardv02.controller;

import com.example.boardv02.dao.BoardDAO;
import com.example.boardv02.dao.CategoryDAO;
import com.example.boardv02.dao.CommentDAO;
import com.example.boardv02.vo.BoardVO;
import com.example.boardv02.vo.CategoryVO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BoardModifyController implements Controller {

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BoardDAO boardDAO = new BoardDAO();
        CategoryDAO categoryDAO = new CategoryDAO();
        // 파일 DAO

        int boardId = Integer.parseInt(request.getParameter("boardId"));
        BoardVO board = boardDAO.getBoardById(boardId);

        CategoryVO category = categoryDAO.getCategoryById(board.getCategoryId());

        request.setAttribute("board", board);
        request.setAttribute("category", category);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/board/modify.jsp");
        dispatcher.forward(request, response);
    }
}
