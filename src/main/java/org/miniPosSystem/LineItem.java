package org.miniPosSystem;

public class LineItem
{
  private Product product;
  private double itemTotalPrice;
  private double quantity;

  public void setProduct(Product product)
  {
    this.product = product;
  }

  public Product getProduct()
  {
    return product;
  }

  public double getQuantity()
  {
    return quantity;
  }

  public void setQuantity(double quantity)
  {
    this.quantity = quantity;
  }

  public double getItemTotalPrice()
  {
    return itemTotalPrice;
  }

  public void setItemTotalPrice(double itemTotalPrice)
  {
    this.itemTotalPrice = itemTotalPrice;
  }

  public double itemTotalPrice(int productCode, double quantity)
  {
    if (quantity <= 0)
    {
      return 0;
    }
    double totalPrice;
    totalPrice = getProduct().getProductPrice(productCode) * quantity;
    return totalPrice;
  }
}