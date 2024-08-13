package utils;

import netscape.javascript.JSObject;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Utils {
    public static void saveUsers(String firstname, String lastname, String userid, String username, String password) throws IOException, ParseException {
        String fileLocation = "./src/test/resources/users.json";
        JSONParser parser= new JSONParser();
        JSONArray empArray =(JSONArray) parser.parse((new FileReader(fileLocation)));
        JSONObject empObj = new JSONObject();
        empObj.put("firstName",firstname);
        empObj.put("lastName",lastname);
        empObj.put("empId",userid);
        empObj.put("userName",username);
        empObj.put("password",password);
        empArray.add(empObj);

        FileWriter fileWriter=new FileWriter(fileLocation);
        fileWriter.write(empArray.toJSONString());
        fileWriter.flush();

    }
    public static JSONArray readJSONData() throws IOException, ParseException {
        String fileLocation = "./src/test/resources/users.json";
        JSONParser parser= new JSONParser();
        JSONArray empArray =(JSONArray) parser.parse((new FileReader(fileLocation)));
        return empArray;
    }
    public static void scroll(WebDriver driver, int height) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,"+height+")");
    }
}
