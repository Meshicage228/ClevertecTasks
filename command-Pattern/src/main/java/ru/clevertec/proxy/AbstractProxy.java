package ru.clevertec.proxy;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AbstractProxy {
    private final Object proxyObject;
    protected List<String> annotatedMethods;
    protected Map<String, Method> methods;

    public AbstractProxy(Object proxyObject) {
        annotatedMethods = new ArrayList<>();
        methods = new HashMap<>();
        this.proxyObject = proxyObject;
        collectInterfacesWithAnnotatedMethods();
    }

    private void collectInterfacesWithAnnotatedMethods(){
        Class<?> aClass = proxyObject.getClass();

        Class<?>[] interfaces = aClass.getInterfaces();

        for (var inter : interfaces) {
            for (var meth : inter.getMethods()) {
                if (meth.isAnnotationPresent(Log.class)) {
                    annotatedMethods.add(meth.getName());
                    methods.put(meth.getName(), meth);
                }
            }
        }
    }

    public Object executeProxyMethod(String methodName, Object... params){
        Class<?> aClass = proxyObject.getClass();
        System.out.println("Started class :" + aClass.getName());
        System.out.println("Started method :" + methodName);
        Method methodToInvoke = methods.get(methodName);
        Object invoked = null;
        try {
            Method method = aClass.getMethod(methodName, methodToInvoke.getParameterTypes());
            invoked = method.invoke(proxyObject, params);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Ended method :" + methodName);
        return invoked;
    }
}
