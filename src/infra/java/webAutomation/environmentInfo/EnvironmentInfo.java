package webAutomation.environmentInfo;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class EnvironmentInfo {

    public static void main(String[] args) {
//        String json = "{'id': 1001, "
//                + "'firstName': 'Lokesh',"
//                + "'lastName': 'Gupta',"
//                + "'email': 'howtodoinjava@gmail.com'}";
//
//        JsonElement jsonElement = JsonParser.parseString(json);
//
//        JsonObject jsonObject = jsonElement.getAsJsonObject();
//
//        System.out.println( jsonObject.get("id") );
//        System.out.println( jsonObject.get("firstName") );
//        System.out.println( jsonObject.get("lastName") );
//        System.out.println( jsonObject.get("email") );

        try {
            JSONParser parser = new JSONParser();
            //Use JSONObject for simple JSON and JSONArray for array of JSON.
            JSONObject data = (JSONObject) parser.parse(
                    new FileReader("src/test/resources/data1.json"));//path to the JSON file.

            String json = data.get("employee-Amarnath").toString();
            System.out.println(json);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

}
