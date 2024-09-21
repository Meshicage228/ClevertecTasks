package ru.clevertec.proxy.realisation;

import ru.clevertec.commands.Command;
import ru.clevertec.commands.impl.GetProduct;
import ru.clevertec.proxy.AbstractProxy;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetProductProxy extends AbstractProxy implements Command {
    private final GetProduct getProduct;

    public GetProductProxy(GetProduct getProduct) {
        super(getProduct);
        this.getProduct = getProduct;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        if (annotatedMethods.contains("execute")){
            return (String) executeProxyMethod("execute", request, response);
        } else {
            return getProduct.execute(request, response);
        }
    }
}
