package mz.sga.ujc.demo.utils;

import com.google.gson.Gson;

public class JsonUtils {
    public static <T> T convertJsonToObject(String body, Class<T> type) {
        Gson gson = new Gson();
        T result = gson.fromJson(body, type);
        return result;
    }

    public static <E> String convertObjectToJson(E element) {
        Gson gson = new Gson();
        return gson.toJson(element);
    }
}