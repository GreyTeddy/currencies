import java.net.URI;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.JSONObject;

/**
 * GetJSON11
 */
public class GetJSON11{

    private JSONObject JSON;
    
//    from fixer.io with free account 
//    private String access_key = "";
//    private String url = "http://data.fixer.io/api/latest?access_key=" + access_key;
    
//    from https://exchangeratesapi.io/
    private String url  = "https://api.exchangeratesapi.io/latest?base=GBP";
//    private String url = "https://jsonplaceholder.typicode.com/todos/1";
    
    public static void main(String[] args) {
    	GetJSON11 getJSON11 = new GetJSON11();
        getJSON11.getJSON();
    }

    public JSONObject getJSON(){
//    	create HttpClient
        HttpClient client = HttpClient.newHttpClient();
//        convert URL to HttpRequest
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
        .thenApply(HttpResponse::body)
        .thenApply(this::store)
        .join();
        return JSON;
    }

    private String store(String responseBody) {
    	JSON = new JSONObject(responseBody);
//    	System.out.println(JSON);
    	return null;
    }
}