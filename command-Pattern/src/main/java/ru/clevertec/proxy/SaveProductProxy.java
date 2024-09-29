package ru.clevertec.proxy;

import ru.clevertec.commands.Command;
import ru.clevertec.commands.impl.SaveProduct;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SaveProductProxy extends AbstractProxy implements Command {
    private final SaveProduct saveProduct;

    public SaveProductProxy(SaveProduct saveProduct) {
        super(saveProduct);
        this.saveProduct = saveProduct;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        if (annotatedMethods.contains("execute")){
            return (String) executeProxyMethod("execute", request, response);
        } else {
            return saveProduct.execute(request, response);
        }
    }
}
