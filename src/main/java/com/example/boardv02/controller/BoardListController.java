package com.example.boardv02.controller;

import com.example.boardv02.dao.BoardDAO;
import com.example.boardv02.dao.CategoryDAO;
import com.example.boardv02.vo.BoardVO;
import com.example.boardv02.vo.CategoryVO;
import com.example.boardv02.vo.SearchCriteriaPaging;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class BoardListController implements Controller {


    @Override
    public void process(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        BoardDAO boardDAO = new BoardDAO();

        SearchCriteriaPaging search = new SearchCriteriaPaging();
        search.setCreatedDateFrom(request.getParameter("searchCreatedDateFrom"));
        search.setCreatedDateTo(request.getParameter("searchCreatedDateTo"));
        search.setCategoryId(request.getParameter("searchCategoryId"));
        search.setText(request.getParameter("searchText"));
        if (request.getParameter("curPage") != null) {
            search.setCurPage(Integer.parseInt(request.getParameter("curPage")));
        };
        int totalCount = boardDAO.getTotalBoardCountWithSearchCriteria(search);
        search.setTotalRowCount(totalCount);
        List<BoardVO> boardList = boardDAO.getBoardListWithPagingAndSearchCriteria(search);

        CategoryDAO categoryDAO = new CategoryDAO();
        List<CategoryVO> categoryList = categoryDAO.getCategoryList();

        request.setAttribute("searchCriteria", search);
        request.setAttribute("totalCount",totalCount);
        request.setAttribute("boardList", boardList);
        request.setAttribute("categoryList",categoryList);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/board/list.jsp");
        dispatcher.forward(request, response);
    }
}
