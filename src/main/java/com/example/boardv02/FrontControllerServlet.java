package com.example.boardv02;

import java.io.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "frontControllerServlet", value = "/board/*")
public class FrontControllerServlet extends HttpServlet {



    @Override
    public void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        String contextPath = request.getContextPath();
        String simpleRequestURI = requestURI.substring(contextPath.length());

        if ("/board/list.jsp".equals(simpleRequestURI)) {
            String message = "clear";
            request.setAttribute("test", message);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/board/list.jsp");
            dispatcher.forward(request, response);
        }

    }
}
