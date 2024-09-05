package org.miniPosSystem;

import org.json.JSONObject;

import java.util.HashMap;

public class Product
{
  private int productCode;
  private String productName;
  private double productPrice;
  private DatabaseManager databaseManager = new DatabaseManager();
  private HashMap<Integer, JSONObject> productsMap = databaseManager.loadDataFromJson("./Product.json", "products");
  JSONObject productsJson = new JSONObject();


  public Product(int productCode)
  {
    if (checkProductCode(productCode))
    {
      this.productCode = productCode;
    }
  }

  public boolean checkProductCode(int productCode)
  {
    if (productsMap.get(productCode) == null)
    {
      return false;
    }
    return true;
  }

  public void setProductCode(int productCode)
  {
    this.productCode = productCode;
  }

  public double getProductCode()
  {
    return productCode;
  }

  public void setProductName(String productName)
  {

    this.productName = productName;
  }


  public String getProductName()
  {
    this.productsJson = productJson(productCode);
    if(productsJson == null){
      return null;
    }
    this.productName = this.productsJson.getString("productName");
    return productName;
  }

  public void setProductPrice(double productPrice)
  {
    this.productPrice = productPrice;
  }


  public double getProductPrice(int productCode)
  {
    this.productsJson = productJson(productCode);
    if (this.productsJson == null)
    {
      return 0;
    }

    this.productPrice = this.productsJson.getDouble("productPrice");
    return productPrice;
  }

  public JSONObject productJson(int productCode)
  {
    if (productsMap.containsKey(productCode))
    {
      return productsMap.get(productCode);
    }
    return null;
  }

  @Override
  public String toString()
  {
    return "Code: " + productCode + ", productName: " + productName + ", productPrice: " + productPrice;
  }
}
