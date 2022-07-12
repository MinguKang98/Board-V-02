package com.example.boardv02;

import com.example.boardv02.controller.BoardListController;
import com.example.boardv02.controller.BoardViewController;
import com.example.boardv02.controller.CommentSaveController;
import com.example.boardv02.controller.Controller;
import com.example.boardv02.dao.BoardDAO;
import com.example.boardv02.dao.CategoryDAO;
import com.example.boardv02.vo.BoardVO;
import com.example.boardv02.vo.CategoryVO;
import com.example.boardv02.vo.SearchCriteriaPaging;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "frontControllerServlet", value = "/board/*")
public class FrontControllerServlet extends HttpServlet {

    private Map<String, Controller> controllerMap = new HashMap<>();

    @Override
    public void init(){
        controllerMap.put("/board/list.jsp", new BoardListController());
        controllerMap.put("/board/view.jsp", new BoardViewController());
        controllerMap.put("/board/commentSave", new CommentSaveController());
    }

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String requestURI = request.getRequestURI();
        String contextPath = request.getContextPath();
        String simpleRequestURI = requestURI.substring(contextPath.length());

        Controller controller = controllerMap.get(simpleRequestURI);
        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        controller.process(request, response);
    }
}
