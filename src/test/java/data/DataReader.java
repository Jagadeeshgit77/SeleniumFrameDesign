package data;

import TestComponents.BaseTest;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

public class DataReader extends BaseTest {
    public List<HashMap<String, String>> getJsonDataToMap() throws IOException {

        //read json to string
        String json= FileUtils.readFileToString(new File(System.getProperty("user.dir") + "\\src\\test\\java\\data\\PurchaseOrder.json"), StandardCharsets.UTF_8);
        //String to HashMap JacksonDataBind mvn dependency

        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String,String>> data = mapper.readValue(json, new TypeReference<List<HashMap<String, String>>>() {
        });
        return data;
        //{map, map}
    }
}
