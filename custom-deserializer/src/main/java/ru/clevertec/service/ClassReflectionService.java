package ru.clevertec.service;

import ru.clevertec.annotation.JsonField;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ClassReflectionService {

    private final Map<Predicate<Class<?>>, Function<Object, Object>> handler = new HashMap<>(){{
        put(String.class::isAssignableFrom, String::valueOf);
        put(aClass -> Integer.class.isAssignableFrom(aClass) || int.class.isAssignableFrom(aClass), value -> Integer.parseInt((String) value));
        put(aClass -> Boolean.class.isAssignableFrom(aClass) || boolean.class.isAssignableFrom(aClass), value -> Boolean.parseBoolean((String) value));
    }};

    public Map<String, Type> collectFieldTypesWithAnnotation(Class<?> clazz) {
        return Arrays.stream(clazz.getDeclaredFields())
                .collect(Collectors.toMap(
                        this::getFieldNameWithAnnotation,
                        Field::getGenericType
                ));
    }

    private String getFieldNameWithAnnotation(Field field){
        return field.isAnnotationPresent(JsonField.class)
                ? field.getAnnotation(JsonField.class).jsonField()
                : field.getName();
    }

    public Map<String, String> collectAnnotationNameFieldWithActualName(Class<?> clazz){
        return Arrays.stream(clazz.getDeclaredFields())
                .collect(Collectors.toMap(
                        field -> field.isAnnotationPresent(JsonField.class)
                                ? field.getAnnotation(JsonField.class).jsonField()
                                : field.getName(),
                        Field::getName
                ));
    }

    public boolean isCustomClass(Class<?> clazz){
        return handler.entrySet().stream()
                .noneMatch(set -> set.getKey().test(clazz));
    }

    public boolean isListType(Class<?> clazz) {
        return List.class.isAssignableFrom(clazz);
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

    public Object convertValue(Object value, Class<?> currentClass) {
        return handler.entrySet().stream()
                .filter(entry -> entry.getKey().test(currentClass))
                .map(entry -> entry.getValue().apply(value))
                .findFirst()
                .orElse(value);
    }
}
