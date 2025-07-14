package util;

import com.google.gson.Gson;
import model.FileMessage;

public class JSONUtil {

    private static final Gson gson = new Gson();

    public static String toJSON(FileMessage msg) {
        return gson.toJson(msg);
    }

    public static FileMessage fromJSON(String json) {
        return gson.fromJson(json, FileMessage.class);
    }
}
