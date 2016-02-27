package rustam.megafood3.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * Created by Rustam on 2/27/2016.
 */
public class Request {

    private final static String TAG = Request.class.getSimpleName();
    private final static String MENU_REQUEST = "https://food-megahackathon.c9users.io/getMenu?restaurant_id=123";

    private Request() {}


    public static List<MenuData> sendMenuRequest(String restaurantName) {
        URL url = null;
        HttpURLConnection conn = null;
        InputStream in = null;
        List<MenuData> list = null;

        try {
            url = new URL(MENU_REQUEST);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.connect();

            in = conn.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }

            list = readMenu(line);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }

    private static List<MenuData> readMenu(String line) {
        //TODO
        return null;
    }


}
