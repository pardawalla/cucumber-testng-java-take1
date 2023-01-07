package MyUtils;

import java.io.IOException;

import org.apache.hc.client5.http.fluent.Request;
import org.apache.hc.client5.http.fluent.Response;
import org.apache.hc.core5.http.io.entity.StringEntity;

public class RestUtils {
    // Get request
    // Reference:
    // https://www.springcloud.io/post/2022-08/httpclient5/#gsc.tab=0

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

        // Crrently using a fake token as proof of concenpt to show how POST requests
        // can be made using bearer auth tokens
        String fakeBearerToken = "myRandomBearerTokeneyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c";
        request.setHeader("Authorization", "Bearer " + fakeBearerToken);

        // JSON parameter string
        // The https://jsontostring.com/ site can be easily used to covert a JSON object
        // to a JSON String.
        // String jsonRequest =
        // "{\"ExistingListingId\":123,\"Category\":\"ABC\",\"Title\":\"ABC\",\"Subtitle\":\"ABC\",\"Description\":[\"ABC\",\"ABC\"],\"StartPrice\":123.0,\"ReservePrice\":123.0,\"BuyNowPrice\":123.0,\"Duration\":0,\"EndDateTime\":\"\\/Date(1514764800)\\/\",\"Pickup\":0,\"PickupSuburbId\":123,\"IsBrandNew\":false,\"AuthenticatedMembersOnly\":false,\"IsClassified\":false,\"OpenHomes\":[{\"Start\":\"\\/Date(1514764800)\\/\",\"End\":\"\\/Date(1514764800)\\/\"},{\"Start\":\"\\/Date(1514764800)\\/\",\"End\":\"\\/Date(1514764800)\\/\"}]}";
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
}
