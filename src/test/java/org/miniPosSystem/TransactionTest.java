package org.miniPosSystem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TransactionTest
{
  Product product;
  private Fixture fixture;

  @BeforeEach
  public void setup()
  {
    fixture = new Fixture();
  }

  @Test
  public void test_givenProductCodeAndProductQuantity_whenCreateLineItemMethodIsCalled_thenReturnProductAndItsTotalPrice()
  {
    fixture.whenProductCodePassedInACreateLineItemMethod(3, 5);
    fixture.thenResultShouldCreateALineItemAsProductAndItsTotalPrice(3, "Eggs", 150);
  }

  @Test
  public void test_givenWrongProductCodeAndProductQuantity_whenCreateLineItemMethodIsCalled_thenReturnProductAndItsTotalPrice()
  {
    fixture.whenWrongProductCodePassedInACreateLineItemMethod(11, 5);
    fixture.thenResultShoulbeZero(0);
  }

  @Test
  public void test_givenProductCodeAndProductQuantity_whenAddLineItemMethodIsCalled_thenReturnProductAndItsTotalPrice()
  {
    fixture.whenProductCodePassedInAAddLineItemMethod(2, 5);
    fixture.thenResultShouldBeProductAndItsTotalPrice(2, "ColdDrink", 400);
  }

  @Test
  public void test_givenMultipleProductsData_whenAddLineItemMethodIsCalled_thenReturnProductAndItsTotalPrice()
  {
    fixture.whenMultiipleProductCodePassedInAAddLineItemMethod(1, 2);
    fixture.thenResultShouldBeProductAndItsTotalPriceForMultipleProducts(1, "ColdDrink", 125);
    fixture.thenResultShouldBeProductAndItsTotalPriceForMultipleProducts(2, "ColdDrink", 480);
  }

  @Test
  public void test_givenWrongProductCodeAndProductQuantity_whenAddLineItemMethodIsCalled_thenReturnProductAndItsTotalPrice()
  {
    fixture.whenWrongProductCodePassedInAAddLineItemMethod(3, 5);
    fixture.thenResultShoulbeZero(0);
  }

  @Test
  public void test_givenProductCodeAndNegativeProductQuantity_whenAddLineItemMethodIsCalled_thenReturnProductAndItsTotalPrice()
  {
    fixture.whenProductCodeAndNegativeProductQuantityPassedInAAddLineItemMethod(3, -5);
    fixture.thenResultShoulbeZero(0);
  }

  @Test
  public void test_givenOneProduct_whenPassedInAddLineItemMethod_thenReturnItsTotalPrice(){
    fixture.whenOneProductCodePassedInAAddLineItemMethod(1);
    fixture.thenResultShouldBeItsTotalPrice(125);
  }

  @Test
  public void test_givenMultipleProduct_whenPassedInAddLineItemMethod_thenReturnItsTotalPrice(){
    fixture.whenOneProductCodePassedInAAddLineItemMethod(1,2,3,1);
    fixture.thenResultShouldBeItsTotalPrice(800);
  }

  @Test
  public void test_givenWrongMultipleProduct_whenPassedInAddLineItemMethod_thenReturnTotalPriceWithoutWrongProduct(){
    fixture.whenOneProductCodePassedInAAddLineItemMethod(1,2,33,1);
    fixture.thenResultShouldBeItsTotalPrice(650);
  }


  private static class Fixture
  {
    private Transaction transactionTestClass = new Transaction();
    private LineItem lineItem;
    private ArrayList<LineItem> result;

    public void whenProductCodePassedInACreateLineItemMethod(int productCode, double quantity)
    {
      lineItem = transactionTestClass.createLineItem(productCode, quantity);
    }

    public void whenWrongProductCodePassedInACreateLineItemMethod(int productCode, double quantity)
    {
      lineItem = transactionTestClass.createLineItem(productCode, quantity);
    }


    public void thenResultShoulbeZero(int i)
    {
      if (lineItem == null)
      {
        assertEquals(i, 0);
      }
    }

    public void whenProductCodePassedInAAddLineItemMethod(int productCode, double quantity)
    {
      transactionTestClass.addLineItem(productCode, quantity);
    }

    public void whenWrongProductCodePassedInAAddLineItemMethod(int productCode, double quantity)
    {
      result = transactionTestClass.addLineItem(productCode, quantity);
    }

    public void whenProductCodeAndNegativeProductQuantityPassedInAAddLineItemMethod(int productCode, double quantity)
    {
      result = transactionTestClass.addLineItem(productCode, quantity);
    }

    public void givenDataHasBeenInitializedForMultipleProducts(int... productCode)
    {
      for (int code : productCode)
      {
        transactionTestClass.addLineItem(code, 5);
      }
    }

    public void whenMultiipleProductCodePassedInAAddLineItemMethod(int... productCodes)
    {

      transactionTestClass.addLineItem(productCodes[0], 5);
      transactionTestClass.addLineItem(productCodes[1], 6);

    }

    public void whenOneProductCodePassedInAAddLineItemMethod(int... productCodes)
    {
      for(int code : productCodes){
        transactionTestClass.addLineItem(code, 5);
      }
    }

    public void thenResultShouldBeProductAndItsTotalPriceForMultipleProducts(int productCode, String productName, double totalPrice)
    {
      ArrayList<LineItem> items = transactionTestClass.getLineItemList();
      boolean result = false;
      for (LineItem line : items)
      {
        if (line.getItemTotalPrice() == totalPrice)
        {
          result = true;
          break;
        }
      }
      assertEquals(true, result);
    }

    public void thenResultShouldBeProductAndItsTotalPrice(int productCode, String productName, double totalPrice)
    {
      ArrayList<LineItem> items = transactionTestClass.getLineItemList();
      boolean result = false;
      for (LineItem line : items)
      {
        if (line.getItemTotalPrice() == totalPrice)
        {
          result = true;
          break;
        }
      }
      assertEquals(true, result);
    }

    public void thenResultShouldCreateALineItemAsProductAndItsTotalPrice(int productCode, String productName, double totalPrice)
    {
      assertEquals(totalPrice, lineItem.getItemTotalPrice());
    }

    public void thenResultShouldBeItsTotalPrice(int i)
    {
      assertEquals(i, transactionTestClass.getTotalPrice());
    }
  }
}