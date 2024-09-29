package ru.clevertec.commands.impl;

import ru.clevertec.commands.Command;
import ru.clevertec.db.DataBase;
import ru.clevertec.db.DataBaseFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Random;

public class SaveProduct implements Command {
    private final DataBase dataBase = DataBaseFactory.getInstance().getDatabase();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String productTitle = request.getParameter("productTitle");

        try {
            dataBase.put(String.valueOf(new Random().nextInt() + 1), productTitle);
            response.setStatus(HttpServletResponse.SC_CREATED);
            return "success";
        } catch (Exception e) {
            request.setAttribute("errorMessage", e.getMessage());
            return "failure";
        }
    }
}
