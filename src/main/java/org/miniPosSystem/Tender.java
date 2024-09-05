package org.miniPosSystem;

import org.json.JSONObject;

import java.util.HashMap;

public class Tender
{
  int tenderCode;
  private String tenderType;
  double paymentAmount;
  private DatabaseManager databaseManager = new DatabaseManager();
  private HashMap<Integer, JSONObject> tendersMap = databaseManager.loadDataFromJson("./Tender.json", "tenders");
//  JSONObject tendersJson = new JSONObject();

  public double getPaymentAmount()
  {
    return paymentAmount;
  }

  public void setPaymentAmount(double paymentAmount)
  {
    this.paymentAmount = paymentAmount;
  }

  public String getTenderTypes()
  {
    return tenderType;
  }

  public void setTenderTypes(String tenderType)
  {
    this.tenderType = tenderType;
  }

  public int getTenderID()
  {
    return tenderCode;
  }

  public void setTenderID(int tenderID)
  {
    this.tenderCode = tenderID;
  }

}