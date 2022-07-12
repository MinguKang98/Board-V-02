package com.example.boardv02.controller;

import com.example.boardv02.dao.BoardDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BoardDeleteController implements Controller {

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BoardDAO boardDAO = new BoardDAO();

        int boardId = Integer.parseInt(request.getParameter("boardId"));
        boardDAO.deleteBoard(boardId);

        response.sendRedirect("list.jsp");
    }
}
