package com.example.boardv02;

import com.example.boardv02.controller.*;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
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
        controllerMap.put("/board/write.jsp", new BoardWriteController());
        controllerMap.put("/board/boardSave", new BoardSaveController());
        controllerMap.put("/board/passwordCheck.jsp", new PasswordCheckController());
        controllerMap.put("/board/passwordConfirm", new PasswordConfirmController());
        controllerMap.put("/board/boardDelete", new BoardDeleteController());
        controllerMap.put("/board/modify.jsp", new BoardModifyController());
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
