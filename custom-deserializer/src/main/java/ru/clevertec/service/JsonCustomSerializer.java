package ru.clevertec.service;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import static java.util.Objects.isNull;
import static ru.clevertec.enums.ConstantChars.*;

public class JsonCustomSerializer {
    private final ClassReflectionService classService;
    private final StringBuilder builder;

    public JsonCustomSerializer() {
        classService = new ClassReflectionService();
        builder = new StringBuilder();
    }

    public String serialize(Object source) throws Exception {
        String string = serializationProcess(source.getClass(), source);
        return string.replace(",\n}", "\n}").replace(",\n]", "\n]");
    }

    private String serializationProcess(Class<?> clazz, Object source) throws Exception {
        Map<String, Type> stringTypeMap = classService.collectFieldTypesWithAnnotation(clazz);
        Map<String, String> fieldNames = classService.collectAnnotationNameFieldWithActualName(clazz);

        builder.append(FIGURE_OPENED).append(NEW_LINE);

        for (Map.Entry<String, Type> entry : stringTypeMap.entrySet()) {
            String jsonKey = entry.getKey();
            Type type = entry.getValue();

            Class<?> currentClass = classService.getInnerClass(type);
            Class<?> currentRawClass = classService.getRawType(type);

            String methodName = getGetterMethodName(fieldNames.get(jsonKey));

            Object result = getValue(type, methodName, source);

            if (isNull(result)) {
                continue;
            }

            String quotedKey = String.format("\"%s\"", jsonKey);
            builder.append(quotedKey).append(COLON);

            if (classService.isListType(currentRawClass)) {
                builder.append(SQUARE_OPENED).append(NEW_LINE);
                for (Object value : (List<?>) result) {
                    serializationProcess(currentClass, value);
                    builder.append(COMMA).append(NEW_LINE);
                }
                builder.append(SQUARE_CLOSED);
            }
            else if (classService.isCustomClass(currentClass)) {
                builder.append(NEW_LINE);
                serializationProcess(currentClass, result);
            }
            else {
                Object value = getValue(type, methodName, source);
                if (value instanceof String) {
                    value = String.format("\"%s\"", value);
                }
                builder.append(value);
            }
            builder.append(COMMA).append(NEW_LINE);
        }
        builder.append(FIGURE_CLOSED);
        return builder.toString();
    }

    private String getGetterMethodName(String jsonKey) {
        return "get" + jsonKey.substring(0, 1).toUpperCase() + jsonKey.substring(1);
    }

    private Object getValue(Type type, String methodName, Object source) throws Exception {
        Class<?> aClass = source.getClass();
        Class<?> rawClass = classService.getRawType(type);

        if (List.class.isAssignableFrom(rawClass)) {
            Method method = aClass.getDeclaredMethod(methodName);
            return method.invoke(source);
        } else {
            Method method = aClass.getMethod(methodName);
            return method.invoke(source);
        }
    }
}
