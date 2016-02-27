package rustam.megafood3.model;

import java.util.List;

/**
 * Created by Rustam on 2/27/2016.
 */
public class MenuData {

    private String id;
    private String image;
    private String name;
    private String desc;
    private String price;
    private List<MenuData> list;
    private String type;

    public MenuData(String id, String image, String name, String desc, String price, String type, List<MenuData> list) {
        this.id = id;
        this.image = image;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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
}
