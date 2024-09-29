package ru.clevertec.commands.impl;

import ru.clevertec.commands.Command;
import ru.clevertec.db.DataBase;
import ru.clevertec.db.DataBaseFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateProduct implements Command {
    private final DataBase dataBase = DataBaseFactory.getInstance().getDatabase();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String newProductTitle = request.getParameter("productTitle");
        String id = request.getParameter("id");

        try {
            dataBase.put(id, newProductTitle);
            response.setStatus(HttpServletResponse.SC_OK);
            return "success";
        } catch (Exception e) {
            request.setAttribute("errorMessage", e.getMessage());
            return "failure";
        }
    }
}
