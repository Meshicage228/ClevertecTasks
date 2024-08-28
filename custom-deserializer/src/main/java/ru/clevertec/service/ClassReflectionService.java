package ru.clevertec.service;

import ru.clevertec.annotation.JsonField;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

public class ClassReflectionService {

    private final Map<Predicate<Class<?>>, Function<Object, Object>> typeConverter = new HashMap<>(){{
        put(String.class::isAssignableFrom, String::valueOf);
        put(aClass -> Integer.class.isAssignableFrom(aClass) || int.class.isAssignableFrom(aClass), value -> Integer.parseInt((String) value));
        put(aClass -> Boolean.class.isAssignableFrom(aClass) || boolean.class.isAssignableFrom(aClass), value -> Boolean.parseBoolean((String) value));
    }};

    public Map<String, Type> collectFieldTypesWithAnnotation(Class<?> clazz) {
        return Arrays.stream(clazz.getDeclaredFields())
                .collect(Collectors.toMap(
                        field -> field.isAnnotationPresent(JsonField.class)
                                ? field.getAnnotation(JsonField.class).jsonField()
                                : field.getName(),
                        Field::getGenericType
                ));
    }

    public Map<String, Type> collectFieldTypes(Class<?> clazz) {
        return Arrays.stream(clazz.getDeclaredFields())
                .collect(Collectors.toMap(Field::getName ,Field::getGenericType));
    }

    public Class<?> getInnerClass(Type type) {
        if (type instanceof ParameterizedType parameterizedType) {
            return (Class<?>) parameterizedType.getActualTypeArguments()[0];
        } else {
            return (Class<?>) type;
        }
    }

    public Class<?> getRawType(Type type) {
        if (type instanceof Class) {
            return (Class<?>) type;
        } else if (type instanceof ParameterizedType parameterizedType) {
            return (Class<?>) parameterizedType.getRawType();
        } else {
            throw new UnsupportedOperationException("Unsupported type: " + type);
        }
    }

    public HashMap<String, String[]> rebuildJsonMap(Class<?> clazz, HashMap<String, String[]> currentMap) {
        HashMap<String, String[]> result = new HashMap<>();

        Arrays.stream(clazz.getDeclaredFields())
                .filter(field -> nonNull(field.getAnnotation(JsonField.class)))
                .forEach(field -> {
                    String jsonField = field.getAnnotation(JsonField.class).jsonField();
                    if (currentMap.containsKey(jsonField)) {
                        result.put(field.getName(), currentMap.get(jsonField));
                        currentMap.remove(jsonField);
                    }
                });

        result.putAll(currentMap);
        return result;
    }

    public Object convertValue(Object value, Class<?> currentClass) {
        return typeConverter.entrySet().stream()
                .filter(entry -> entry.getKey().test(currentClass))
                .map(entry -> entry.getValue().apply(value))
                .findFirst()
                .orElse(value);
    }
}
