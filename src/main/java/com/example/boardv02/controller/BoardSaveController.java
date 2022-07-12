package com.example.boardv02.controller;

import com.example.boardv02.dao.BoardDAO;
import com.example.boardv02.vo.BoardVO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BoardSaveController implements Controller{

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        BoardDAO boardDAO = new BoardDAO();
        // 파일 DAO

        BoardVO boardVO = new BoardVO();
        int categoryID = Integer.parseInt(request.getParameter("category"));
        String user = request.getParameter("user");
        String password = request.getParameter("password");
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String file1 = request.getParameter("file1");
        String file2 = request.getParameter("file2");
        String file3 = request.getParameter("file3");
        String fileArray[] = {file1, file2, file3};

        // 유효성 검사

        boolean fileExist = false;
        for (String file : fileArray) {
            if (!("".equals(file))) {
                fileExist = true;
                break;
            }
        }

        boardVO.setCategoryId(categoryID);
        boardVO.setUser(user);
        boardVO.setPassword(password);
        boardVO.setTitle(title);
        boardVO.setContent(content);
        boardVO.setFileExist(fileExist);

        int boardId = boardDAO.addBoard(boardVO);
        response.sendRedirect("view.jsp?boardId=" + boardId);
    }
}
