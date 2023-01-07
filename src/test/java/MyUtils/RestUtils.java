package MyUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

import org.apache.hc.client5.http.fluent.Request;
import org.apache.hc.client5.http.fluent.Response;
import org.apache.hc.core5.http.io.entity.StringEntity;

public class RestUtils {

    static SysOutPrintlnColored printToConsole = new SysOutPrintlnColored();

    // Get request (Reference: https://www.springcloud.io/post/2022-08/httpclient5/#gsc.tab=0)
    public static String getRequest(String url) {
        String result = null;
        try {
            Response response = Request.get(url).execute();
            result = response.returnContent().asString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    // POST request
    // Reference(s):
    // https://www.springcloud.io/post/2022-08/httpclient5/#gsc.tab=0
    // https://www.tabnine.com/code/java/methods/org.apache.http.client.fluent.Request/setHeader
    public static Response postRequest(String url, String jsonData) throws IOException {
        // String result = null;
        Response resp = null;
        Request request = Request.post(url).setHeader("Content-type", "application/json");

        String BearerToken = getBearerToken();
        request.setHeader("Authorization", "Bearer " + BearerToken);

        String jsonRequest = jsonData;
        StringEntity jsonRequestEntity = new StringEntity((jsonRequest));
        request = request.body(jsonRequestEntity);
        try {
            resp = request.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resp;
    }

    // helper method
    private static String getBearerToken() throws IOException {
        // References:
        // https://stackoverflow.com/questions/326390/how-do-i-create-a-java-string-from-the-contents-of-a-file
        // https://reactgo.com/java-convert-string-to-path/
        String workingDir = System.getProperty("user.dir");
        String myDataTxtPath = workingDir + "/src/test/resources/data.txt";
        String bearerToken = Files.readString(Paths.get(myDataTxtPath), StandardCharsets.UTF_8);
        printToConsole.purplePrint(bearerToken);
        // Reference :
        // https://stackoverflow.com/questions/29061782/java-read-txt-file-to-hashmap-split-by
        HashMap<String, String> map = new HashMap<String, String>();
        String[] parts = bearerToken.split(":", 2);
        String key = parts[0];
        String value = parts[1];
        map.put(key, value);
        printToConsole.purplePrint("BEARER_TOKEN is " + map.get("BEARER_TOKEN"));
        return(map.get("BEARER_TOKEN"));
    }
}
