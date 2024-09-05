package org.miniPosSystem;

import org.json.JSONObject;

import java.util.HashMap;

public class Promotion
{

  private int promotionCode;
  private String type; // "percentage" or "value"
  private int triggerQuantity;
  private double discount;

  private DatabaseManager databaseManager = new DatabaseManager();
  private HashMap<Integer, JSONObject> promotionsMap = databaseManager.loadDataFromJson("./Promotion.json", "promotions");
  JSONObject promotionsJson = new JSONObject();

  public Promotion(int promotionCode) {
    this.promotionCode = promotionCode;
    promotionsJson(promotionCode);
  }

  // Getters and Setters
  public int getpromotionCode() {
    return promotionCode;
  }

  public void setPromotionCode(int promotionCode) {
    this.promotionCode = promotionCode;
  }

  public String getPromotionType() {
    return type;
  }

  public void setPromotionType(String type) {
    this.type = type;
  }

  public int getTriggerQuantity() {
    return triggerQuantity;
  }

  public void setTriggerQuantity(int triggerQuantity) {
    this.triggerQuantity = triggerQuantity;
  }

  public double getDiscount() {
    return discount;
  }

  public void setDiscount(double discount) {
    this.discount = discount;
  }

  public JSONObject promotionsJson(int promotionCode)
  {
    if(promotionsMap.containsKey(promotionCode)){
      setPromotionType(promotionsMap.get(promotionCode).getString("promotionType"));
      setTriggerQuantity(promotionsMap.get(promotionCode).getInt("triggerQuantity"));
      setDiscount(promotionsMap.get(promotionCode).getDouble("discount"));
      return promotionsMap.get(promotionCode);
    }
    return null;
  }
}

