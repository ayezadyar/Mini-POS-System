package org.miniPosSystem;

import java.util.ArrayList;

public class Transaction
{

  private ArrayList<LineItem> lineItemList = new ArrayList<>();
  private double totalPrice =0 ;
  private int TransactionID;
  LineItem lineItem;

  public int getTransactionID()
  {
    return TransactionID;
  }

  public void setTransactionID(int transactionID)
  {
    TransactionID = transactionID;
  }

  public ArrayList<LineItem> getLineItemList()
  {
    return lineItemList;
  }

  public void setLineItemList(LineItem lineItemList)
  {
    this.lineItemList.add(lineItemList);
  }


  public double getTotalPrice()
  {
    return totalPrice;
  }

  public void setTotalPrice(double totalPrice)
  {
    this.totalPrice += totalPrice;
  }

  public LineItem createLineItem(int productCode, double quantity)
  {
    lineItem = new LineItem();
    lineItem.setProduct(new Product(productCode));
    lineItem.setItemTotalPrice(lineItem.itemTotalPrice(productCode, quantity));
    return lineItem;
  }

  public ArrayList<LineItem> addLineItem(int productCode, double quantity)
  {
    lineItemList.add(createLineItem(productCode, quantity));
    setTotalPrice(lineItem.getItemTotalPrice());
    return this.lineItemList;
  }

//  public void calculateTotalLineItemPrice(LineItem lineItemList){
//    double totalPrice = 0;
//    lineItemList.getItemTotalPrice();
//    System.out.println(lineItemList.getItemTotalPrice());
//  }

}
