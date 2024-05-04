package resources;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.*;
import java.util.Properties;

public class Untils {
    public  static RequestSpecification requestSpecification;


    public RequestSpecification requestSpecification() throws IOException {
        if(requestSpecification==null) {
            PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
            requestSpecification = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUrl")).addQueryParam("key", "qaclick123")
                    .addFilter(RequestLoggingFilter.logRequestTo(log))
                    .addFilter(ResponseLoggingFilter.logResponseTo(log))
                    .setContentType(ContentType.JSON).build();
            return requestSpecification;
        }
        return requestSpecification;
    }
    public static String getGlobalValue(String key) throws IOException {
        Properties properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream("src/test/java/resources/global.properties");
        properties.load(fileInputStream);
        return properties.getProperty(key);
    }
    public String getJsonPath(Response response, String key){
        String resp=response.asString();
        JsonPath js = new JsonPath(resp);
        return js.get(key).toString();

    }
}
