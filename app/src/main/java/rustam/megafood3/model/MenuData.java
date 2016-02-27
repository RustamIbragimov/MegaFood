package rustam.megafood3.model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rustam on 2/27/2016.
 */
public class MenuData {

    private String id;
    private Bitmap image;
    private String name;
    private String desc;
    private String price;
    private List<MenuData> list;
    private String type;

    public MenuData() {
        list = new ArrayList<>();
    }

    public MenuData(String id, String image, String name, String desc, String price, String type, List<MenuData> list) {
        this.id = id;
        this.image = getBitmapFromURL(image);
        this.name = name;
        this.desc = desc;
        this.price = price;
        this.list = list;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(String image) {

        this.image = getBitmapFromURL(image);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public List<MenuData> getList() {
        return list;
    }

    public void setList(List<MenuData> list) {
        this.list = list;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isSingle(){
        return type.equals(Request.SINGLE);
    }

    private Bitmap getBitmapFromURL(String src) {
        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            // Log exception
            return null;
        }
    }
}
