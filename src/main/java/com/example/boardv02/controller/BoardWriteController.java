package com.example.boardv02.controller;

import com.example.boardv02.dao.CategoryDAO;
import com.example.boardv02.vo.CategoryVO;
import com.example.boardv02.vo.SearchCriteriaPaging;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class BoardWriteController implements Controller {

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CategoryDAO categoryDAO = new CategoryDAO();

//        SearchCriteriaPaging search = new SearchCriteriaPaging();
//        search.setCreatedDateFrom(request.getParameter("searchCreatedDateFrom"));
//        search.setCreatedDateTo(request.getParameter("searchCreatedDateTo"));
//        search.setCategoryId(request.getParameter("searchCategoryId"));
//        search.setText(request.getParameter("searchText"));
//        if (request.getParameter("curPage") != null) {
//            search.setCurPage(Integer.parseInt(request.getParameter("curPage")));
//        };

        List<CategoryVO> categoryList = categoryDAO.getCategoryList();

//        request.setAttribute("searchCriteria",search);
        request.setAttribute("categoryList", categoryList);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/board/write.jsp");
        dispatcher.forward(request, response);
    }
}
