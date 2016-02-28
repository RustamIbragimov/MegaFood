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
import java.util.Map;

/**
 * Created by Rustam on 2/27/2016.
 */
public class Request {

    public final static String SINGLE = "item";
    public final static String GROUP = "group";

    private final static String TAG = Request.class.getSimpleName();
    private final static String MENU_REQUEST = "https://food-megahackathon.c9users.io/getMenu?restaurant_id=2";
    private final static String CHECKOUT_REQUEST = "https://food-megahackathon.c9users.io/pay?";
    private final static String ORDER_REQUEST = "https://food-megahackathon.c9users.io/sendOrders?";
    private final static String CHECK_STATUS = "https://food-megahackathon.c9users.io/getOrderStatus?";

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
    private final static String ORDER_ID = "order_id";
    private final static String CARD_NUMBER = "PAN";
    private final static String CARD_HOLDER = "holder";
    private final static String EXP_DATE = "exp";
    private final static String CVV = "cvv";



    private Request() {}

    public static String checkStatus(String orderID) {
        URL url = null;
        HttpURLConnection conn = null;
        InputStream in = null;
        String status = null;
        String urlrequest = CHECK_STATUS;
        urlrequest+="order_id="+status;
        try {
            url = new URL(urlrequest);
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

            status = parseOrderId(sb.toString());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return status;
    }

    public static String checkOut(String ordID, String cardNumber, String cardHolder, String expDate, String cvv) {
        URL url = null;
        HttpURLConnection conn = null;
        InputStream in = null;
        String status = null;
        String urlrequest = CHECKOUT_REQUEST;
        urlrequest+=ORDER_ID+'='+ordID;
        urlrequest+='&'+CARD_NUMBER+'='+cardNumber;
        urlrequest+='&'+CARD_HOLDER+'='+cardHolder;
        urlrequest+='&'+EXP_DATE+'='+expDate;
        urlrequest+='&'+CVV+'='+cvv;
        try {
            url = new URL(urlrequest);
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

            status = parseOrderId(sb.toString());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return status;
    }

    public static String sendOrderRequest(Map<String, Integer> order) {
        URL url = null;
        HttpURLConnection conn = null;
        InputStream in = null;
        String orderId = null;
        String urlrequest = ORDER_REQUEST;
        urlrequest+="initiator=Козлов+Дмитрий";
        for(Map.Entry<String, Integer> entry: order.entrySet()){
            urlrequest+='&'+entry.getKey()+'='+entry.getValue();
        }
        try {
            url = new URL(urlrequest);
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

            orderId = parseOrderId(sb.toString());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return orderId;
    }

    private static String parseOrderId(String json) throws JSONException {
        JSONObject mainObj = new JSONObject(json);
        String status = mainObj.getString(RESPONSE_CODE);
        if (!status.equals("OK")) {
            String error = mainObj.getString(ERROR);
            Log.e(TAG, error);
            return null;
        }
        return mainObj.getString(RESULT);
    }

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

            list = readMenu(sb.toString());

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
            Log.v(TAG, line);
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
                    MenuData groupItem = new MenuData();
                    String name = obj.getString(TITLE);
                    String desc = obj.getString(DESC);
                    String image = obj.getString(IMAGE);

                    groupItem.setName(name);
                    groupItem.setDesc(desc);
                    groupItem.setImage(image);
                    groupItem.setType(GROUP);

                    JSONArray innerArr = obj.getJSONArray(ITEMS);

                    for (int j = 0; j < innerArr.length(); j++) {
                        JSONObject innerObj = innerArr.getJSONObject(j);
                        MenuData item = getMenuData(innerObj);
                        groupItem.getList().add(item);
                    }
                    list.add(groupItem);

                }
                else {
                    MenuData item = getMenuData(obj);
                    list.add(item);
                }
            }

        } catch (JSONException e) {

        }

        return list;
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
