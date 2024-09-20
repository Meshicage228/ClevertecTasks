package ru.clevertec.servlet;

import ru.clevertec.commands.CommandInvoker;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/servletDriver")
public class ServletDriver extends HttpServlet {
    private CommandInvoker commandInvoker;

    @Override
    public void init() {
        commandInvoker = new CommandInvoker();
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) {
        String command = request.getParameter("command");

        if (command != null) {
            commandInvoker.getCommand(command)
                    .ifPresentOrElse(
                            service -> processServiceResponse(service.execute(request, response), request, response),
                            () -> response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR)
                    );
        }
    }

    private void processServiceResponse(String executed, HttpServletRequest request, HttpServletResponse response) {
        try {
            if (executed.equals("success")) {
                response.sendRedirect("success.jsp");
            } else if (executed.equals("failure")) {
                request.getRequestDispatcher("failure.jsp").forward(request, response);
            } else {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        } catch (IOException | ServletException e) {
            throw new RuntimeException(e);
        }
    }
}
