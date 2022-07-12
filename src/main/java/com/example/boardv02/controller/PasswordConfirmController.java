package com.example.boardv02.controller;

import com.example.boardv02.dao.BoardDAO;
import com.example.boardv02.vo.BoardVO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PasswordConfirmController implements Controller {

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BoardDAO boardDAO = new BoardDAO();

        int boardId = Integer.parseInt(request.getParameter("boardId"));
        String type = request.getParameter("type");
        String inputPassword = request.getParameter("password");

        BoardVO board = boardDAO.getBoardById(boardId);
        String originPassword = board.getPassword();

        if (inputPassword.equals(originPassword)) {
            // 같으면 type 에 따라 다음 절차
            if (type.equals("modify")) { // 게시판 수정
                response.sendRedirect("modify.jsp?boardId=" + boardId);
            }

            if (type.equals("delete")) { // 게시판 삭제
                response.sendRedirect("boardDelete?boardId=" + boardId);
            }
        }
        else{
            // 다르면 passwordCheck 로
            request.setAttribute("confirm", "fail");
            RequestDispatcher dispatcher = request.getRequestDispatcher(
                    "/WEB-INF/board/passwordCheck.jsp");
            dispatcher.forward(request, response);

        }
    }
}
