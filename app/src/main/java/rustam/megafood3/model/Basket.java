package rustam.megafood3.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rustam on 2/27/2016.
 */
public class Basket {

    private List<MenuData> list;
    private static Basket instance = null;

    private Basket() {
        this.list = new ArrayList<>();
    }

    public static Basket getInstance() {
        if (instance == null) {
            instance = new Basket();
        }
        return instance;
    }

    public void addToBasket(MenuData item) {
        list.add(item);
    }

    public List<MenuData> getList() {
        return list;
    }

    public void setList(List<MenuData> list) {
        this.list = list;
    }
}
