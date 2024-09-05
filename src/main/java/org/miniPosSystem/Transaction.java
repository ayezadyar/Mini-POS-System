package org.miniPosSystem;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Transaction
{
  private static final Logger logger = LoggerFactory.getLogger(Transaction.class);

  private ArrayList<LineItem> lineItemList = new ArrayList<>();
  private ArrayList<Tender> tenderList = new ArrayList<>();
  private double totalPrice = 0;
  private int TransactionID;
  LineItem lineItem;
  private Tender tender;


  public ArrayList<Tender> getTenderList()
  {
    return tenderList;
  }

  public void setTenderList(ArrayList<Tender> tenderList)
  {
    this.tenderList = tenderList;
  }

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
    lineItem.setQuantity(quantity);
    lineItem.setItemTotalPrice(lineItem.itemTotalPrice(productCode, quantity));
    if (lineItem == null)
    {
      return null;
    }
    logger.debug("Product Code:{}   Name:{}   Quantity :{}  Price :{}", productCode, lineItem.getProduct().getProductName(), quantity, lineItem.getItemTotalPrice());
    return lineItem;

  }

  public ArrayList<LineItem> addLineItem(int productCode, double quantity)
  {

    lineItemList.add(createLineItem(productCode, quantity));
    setTotalPrice(lineItem.getItemTotalPrice());

    return lineItemList;
  }

  public Tender createTender(String tenderType, double paymentAmount)
  {

    logger.debug("TenderType: {}     Bill: {} ", tenderType, getTotalPrice());
    logger.debug("           Payment Amount: {} ", paymentAmount);

    tender = new Tender();
    tender.setTenderTypes(tenderType);
    tender.setPaymentAmount(paymentAmount);

    return tender;
  }

  public ArrayList<Tender> addTender(String tenderType, double paymentAmount)
  {
    if (paymentAmount < 0)
    {
      return null;
    }
    tenderList.add(createTender(tenderType, paymentAmount));
    processPayment(paymentAmount);

    return tenderList;
  }

  public void processPayment(double paymentAmount)
  {
    this.totalPrice -= paymentAmount;
    logger.debug("Balance : {}", this.totalPrice);
  }

  private void recalculateTotalPrice()
  {
    this.totalPrice = lineItemList.stream().mapToDouble(LineItem::getItemTotalPrice).sum();
    logger.debug("                  Total Bill: {}", totalPrice);
  }

  public void applyPromotions(ArrayList<Promotion> promotions)
  {
    logger.info("Applying promotions");
    for (Promotion promotion : promotions)
    {
      for (LineItem lineItem : lineItemList)
      {
        int quantity = (int) lineItem.getQuantity();  // Assuming getQuantity() returns a double

        if (quantity >= promotion.getTriggerQuantity())
        {
          if ("percentage".equalsIgnoreCase(promotion.getPromotionType()))
          {
            double discountAmount = (lineItem.getItemTotalPrice() * promotion.getDiscount()) / 100;
            lineItem.setItemTotalPrice(lineItem.getItemTotalPrice() - discountAmount);
            logger.debug("Promotion Applied On Product: {}", lineItem.getProduct().getProductName());
            logger.debug("Discount Amount :{}", discountAmount);
          }
          else if ("value".equalsIgnoreCase(promotion.getPromotionType()))
          {
            double discountAmount = promotion.getDiscount();
            lineItem.setItemTotalPrice(lineItem.getItemTotalPrice() - discountAmount);
            logger.debug("Promotion Applied On Product: {}", lineItem.getProduct().getProductName());
            logger.debug("             Discount Amount: {}", discountAmount);
          }
        }
      }
    }
    recalculateTotalPrice();
  }
}