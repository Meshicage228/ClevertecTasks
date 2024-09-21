package ru.clevertec.proxy.realisation;

import ru.clevertec.commands.Command;
import ru.clevertec.commands.impl.UpdateProduct;
import ru.clevertec.proxy.AbstractProxy;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateProductProxy extends AbstractProxy implements Command {
    private final UpdateProduct updateProduct;

    public UpdateProductProxy(UpdateProduct updateProduct) {
        super(updateProduct);
        this.updateProduct = updateProduct;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        if (annotatedMethods.contains("execute")){
            return (String) executeProxyMethod("execute", request, response);
        } else {
            return updateProduct.execute(request, response);
        }
    }
}
