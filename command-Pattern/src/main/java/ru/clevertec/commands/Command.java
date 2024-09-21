package ru.clevertec.commands;

import ru.clevertec.proxy.Log;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {
    @Log
    String execute(HttpServletRequest request, HttpServletResponse response);
}
