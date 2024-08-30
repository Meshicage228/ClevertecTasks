package ru.clevertec.service;

import ru.clevertec.exception.KeyMismatchException;
import ru.clevertec.util.JsonUtils;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.*;

import static ru.clevertec.enums.ConstantChars.*;

public class JsonCustomDeserializer {
    private final String json;

    private final Class<?> clazz;

    private List<Object> innerValues = new ArrayList<>();

    private final ClassReflectionService classService;

    private final JsonUtils jsonUtils;

    public JsonCustomDeserializer(String json, Class<?> clazz) {
        this.clazz = clazz;
        this.json = json;

        classService = new ClassReflectionService();
        jsonUtils = new JsonUtils();
    }

    @SuppressWarnings("unchecked")
    public <T> T deserialize() throws Exception {
        HashMap<String, String[]> jsonMap = jsonUtils.parseJsonIntoMap(json);

        Map<String, Type> classFieldMeta = classService.collectFieldTypesWithAnnotation(clazz);

        compareJsonAndClassFieldsKeys(jsonMap, classFieldMeta);

        return (T) createInstance(jsonMap, clazz);
    }

    private void compareJsonAndClassFieldsKeys(HashMap<String, String[]> jsonMap, Map<String, Type> classFieldMeta) {
        for (Map.Entry<String, String[]> entry : jsonMap.entrySet()) {
            String jsonKey = entry.getKey();
            String[] jsonValues = entry.getValue();

            if (!classFieldMeta.containsKey(jsonKey)) {
                throw new KeyMismatchException(jsonKey);
            }

            Type type = classFieldMeta.get(jsonKey);
            Class<?> innerClass = classService.getInnerClass(type);
            Class<?> currentRawClass = classService.getRawType(type);

            if (classService.isListType(currentRawClass) || isInnerObject(jsonValues[0])) {
                HashMap<String, String[]> valueMap = jsonUtils.parseInnerArray(jsonValues[0]);

                compareJsonAndClassFieldsKeys(valueMap, classService.collectFieldTypesWithAnnotation(innerClass));
            }
        }
    }

    @SuppressWarnings("deprecation")
    private <T> T createInstance(HashMap<String, String[]> jsonMap, Class<T> clazz) throws Exception {
        Map<String, Type> stringTypeMap = classService.collectFieldTypes(clazz);
        Map<String, String> actualFieldNames = classService.collectAnnotationNameFieldWithActualName(clazz);

        T source = clazz.newInstance();

        for (var entry : jsonMap.entrySet()) {
            String jsonKey = entry.getKey();
            String actualFieldName = actualFieldNames.get(jsonKey);
            String[] jsonValues = entry.getValue();
            Type type = stringTypeMap.get(actualFieldName);

            Class<?> currentClass = classService.getInnerClass(type);
            Class<?> currentRawClass = classService.getRawType(type);

            String methodName = getSetterMethodName(actualFieldName);

            if (classService.isListType(currentRawClass)) {
                createInnerListInstances(jsonValues, currentClass);
                setValue(type, methodName, innerValues, source);
                innerValues = new ArrayList<>();
            } else if (isInnerObject(jsonValues[0])) {
                Object innerObj = createInstance(jsonUtils.parseInnerArray(jsonValues[0]), currentClass);
                setValue(type, methodName, innerObj, source);
            } else {
                setValue(type, methodName, jsonValues[0], source);
            }
        }
        return source;
    }

    private String getSetterMethodName(String jsonKey) {
        return "set" + jsonKey.substring(0, 1).toUpperCase() + jsonKey.substring(1);
    }

    private boolean isInnerObject(String jsonValue) {
        return jsonValue.contains(SPEC_DELIMITER.toString()) || jsonValue.contains(COLON.toString());
    }

    private void createInnerListInstances(String[] jsonValues, Class<?> currentClass) throws Exception {
        for (String jsonValue : jsonValues) {
            Object instance = createInstance(jsonUtils.parseInnerArray(jsonValue), currentClass);
            innerValues.add(instance);
        }
    }

    private void setValue(Type type, String methodName, Object value, Object source) throws Exception {
        Class<?> sourceClass = source.getClass();
        Class<?> currentClass = classService.getInnerClass(type);
        Class<?> rawClass = classService.getRawType(type);

        if (List.class.isAssignableFrom(rawClass)) {
            Method method = sourceClass.getMethod(methodName, rawClass);
            method.invoke(source, innerValues);
        } else {
            Method method = sourceClass.getMethod(methodName, currentClass);
            Object convertedValue = classService.convertValue(value, currentClass);
            method.invoke(source, convertedValue);
        }
    }
}
