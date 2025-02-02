package webAutomation.json;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;

public class JsonCommon {

    public static String prettyPrinting(JsonElement jsonObject) {
        return  new GsonBuilder()
                .setPrettyPrinting()
                .create()
                .toJson(jsonObject);
    }

}
