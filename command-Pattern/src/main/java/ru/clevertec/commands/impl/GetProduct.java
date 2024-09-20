package ru.clevertec.commands.impl;

import ru.clevertec.commands.Command;
import ru.clevertec.db.DataBase;
import ru.clevertec.db.SingletonDataBase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetProduct implements Command {
    private final DataBase dataBase = SingletonDataBase.getDatabase();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String productId = request.getParameter("id");

        try {
            System.out.println(dataBase.get(productId));
            response.setStatus(HttpServletResponse.SC_OK);
            return "success";
        } catch (Exception e) {
            request.setAttribute("errorMessage", e.getMessage());
            return "failure";
        }
    }
}
