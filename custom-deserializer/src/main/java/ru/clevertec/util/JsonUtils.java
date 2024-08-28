package ru.clevertec.util;

import ru.clevertec.exception.InvalidJsonException;

import java.util.*;
import java.util.function.Predicate;

import static ru.clevertec.enums.ConstantChars.*;
import static org.apache.commons.lang3.StringUtils.countMatches;

public class JsonUtils {

    private final List<Predicate<String>> handlers = Arrays.asList(
            json -> (countMatches(json, FIGURE_OPENED.getSymbol()) - countMatches(json, FIGURE_CLOSED.getSymbol())) == 0,
            json -> (countMatches(json, SQUARE_OPENED.getSymbol()) - countMatches(json, SQUARE_CLOSED.getSymbol())) == 0,
            json -> json.startsWith(FIGURE_OPENED.toString()),
            json -> json.endsWith(FIGURE_CLOSED.toString())
    );

    public HashMap<String, String[]> parseInnerArray(String jsonValues) {
        HashMap<String, String[]> newMap = new HashMap<>();
        String[] split = jsonValues.split("\\|");

        for (var str : split) {
            String[] split1 = str.split(COLON.toString());
            newMap.put(split1[0].replace("\"", "").trim(), new String[]{split1[1].trim()});
        }
        return newMap;
    }

    public HashMap<String, String[]> parseJsonIntoMap(String json) {
        validInputJson(json);

        HashMap<String, String[]> JSONmap = new LinkedHashMap<>();
        StringBuilder builder = new StringBuilder(json);
        builder.deleteCharAt(0);
        builder.deleteCharAt(builder.length() - 1);
        builder = replaceCommaBySpecialDelimiter(builder);

        for (String object : builder.toString().split(COMMA.toString())) {

            String[] objectValue = object.split(
                    COLON.toString(), 2);

            if (objectValue.length == 2) {
                JSONmap.put(
                        objectValue[0].replaceAll("[{\n\"]", "").trim(),
                        objectValue[1].replaceAll("[{}\\[\\]\n\"]", "").trim().split(DELIMITER_OBJECTS.toString()));
            }
        }
        return JSONmap;
    }

    private void validInputJson(String json) {
        if (handlers.stream()
                .anyMatch(predicate -> !predicate.test(json.trim()))) {
            throw new InvalidJsonException();
        }
    }

    private StringBuilder replaceCommaBySpecialDelimiter(StringBuilder arg) {
        boolean inJsonArray = false;
        boolean inInnerObject = false;

        for (int i = 0; i < arg.length(); i++) {
            char c = arg.charAt(i);

            if (c == FIGURE_OPENED.getSymbol()) {
                inInnerObject = true;
            } else if (c == FIGURE_CLOSED.getSymbol()) {
                inInnerObject = false;
            } else if (c == SQUARE_OPENED.getSymbol()) {
                inJsonArray = true;
            } else if (c == SQUARE_CLOSED.getSymbol()) {
                inJsonArray = false;
            } else if (c == COMMA.getSymbol()) {
                if (inJsonArray && !inInnerObject) {
                    arg.setCharAt(i, DELIMITER_OBJECTS.getSymbol());
                } else if (inInnerObject) {
                    arg.setCharAt(i, SPEC_DELIMITER.getSymbol());
                }
            }
        }
        return arg;
    }
}
