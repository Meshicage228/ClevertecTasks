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
    public void init() throws ServletException {
        commandInvoker = new CommandInvoker();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String command = req.getParameter("command");

        if(command != null){
            commandInvoker.invoke(command);
        }

        super.doGet(req, resp);
    }
}
