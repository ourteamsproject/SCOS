package es.source.code.model;

import java.io.Serializable;

/**
 * Created by Cristo on 2017/10/15.
 */

public class Food implements Serializable {
    public int foodID;
    public String foodName;
    public String foodPrice;
    public int saleNumber;
    public  String type;

    public int getFoodID() {
        return foodID;
    }

    public void setFoodID(int foodID) {
        this.foodID = foodID;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(String foodPrice) {
        this.foodPrice = foodPrice;
    }

    public int getSaleNumber() {
        return saleNumber;
    }

    public void setSaleNumber(int saleNumber) {
        this.saleNumber = saleNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
