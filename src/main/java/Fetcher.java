import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.HttpURLConnection;


/**
 * Created by Aldres on 08.02.2018.
 */
public class Fetcher {
    Gson gson;

    public void registerAdapter() {
         gson = new GsonBuilder()
                .registerTypeAdapter(RateObject.class, new RatesDeserializer())
                .create();
    }

    public ApiResponse getJsonFromUrl (String urlString) throws IOException{
        registerAdapter();
        ApiResponse response = null;
        HttpURLConnection connection;
        try {
            URL url = new URL(urlString);
             connection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = connection.getInputStream();

            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw  new IOException("Connection error: " + connection.getResponseMessage());
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
             response = gson.fromJson(reader, ApiResponse.class);
             connection.disconnect();
             inputStream.close();
             reader.close();
        }
        catch (IOException e){
            e.getMessage();
        }
        return response;
    }
}

