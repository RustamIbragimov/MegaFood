package rustam.megafood3.model;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rustam on 2/27/2016.
 */
public class Request {

    public final static String SINGLE = "item";
    public final static String GROUP = "group";

    private final static String TAG = Request.class.getSimpleName();
    private final static String MENU_REQUEST = "https://food-megahackathon.c9users.io/getMenu?restaurant_id=123";

    private final static String RESPONSE_CODE = "response_code";
    private final static String ERROR = "error";
    private final static String RESULT = "result";
    private final static String DESC = "description";
    private final static String ID = "id";
    private final static String TITLE = "name";
    private final static String TYPE = "type";
    private final static String IMAGE = "image";
    private final static String PRICE = "price";
    private final static String ITEMS = "items";



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
        List<MenuData> list = new ArrayList<>();
        try {
            JSONObject mainObj = new JSONObject(line);
            String status = mainObj.getString(RESPONSE_CODE);
            if (!status.equals("OK")) {
                String error = mainObj.getString(ERROR);
                Log.e(TAG, error);
                return null;
            }

            JSONArray mainArr = mainObj.getJSONArray(RESULT);

            for (int i = 0; i < mainArr.length(); i++) {
                JSONObject obj = mainArr.getJSONObject(i);

                String type = obj.getString(TYPE);
                if (type.equals(GROUP)) {
                    String name = obj.getString(TITLE);
                    String desc = obj.getString(DESC);

                    JSONArray innerArr = obj.getJSONArray(ITEMS);

                    for (int j = 0; j < innerArr.length(); j++) {
                        JSONObject innerObj = innerArr.getJSONObject(j);
                        MenuData item = getMenuData(innerObj);
                        list.add(item);
                    }

                }
                else {
                    MenuData item = getMenuData(obj);
                    list.add(item);
                }
            }

        } catch (JSONException e) {

        }

        return null;
    }

    private static MenuData getMenuData(JSONObject obj) throws JSONException {
        String name = obj.getString(TITLE);
        String desc = obj.getString(DESC);
        String image = obj.getString(IMAGE);
        String price = obj.getString(PRICE);
        String id = obj.getString(ID);

        MenuData item = new MenuData(id, image, name, desc, price, SINGLE, null);
        return item;
    }


}
