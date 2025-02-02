package webAutomation.json;

import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import webAutomation.logger.LoggerManager;

import java.io.FileReader;
import java.io.IOException;

public class JsonRead {
protected static Logger logger = LoggerManager.getLogger(JsonRead.class);

public static void readJson(String key){
    try {
        JSONParser parser = new JSONParser();
        //Use JSONObject for simple JSON and JSONArray for array of JSON.
        JSONObject data = (JSONObject) parser.parse(
        new FileReader("src/test/resources/data1.json"));//path to the JSON file.
        String getData = data.get(key).toString();
        logger.debug("Key: "+key+" | data: "+getData);
    } catch (IOException | ParseException e) {
        e.printStackTrace();
    }
}
    public static void main(String[] args) {
        readJson("employee-Vidya");
    }


}
