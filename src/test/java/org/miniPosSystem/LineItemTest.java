package org.miniPosSystem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LineItemTest
{
  private Product product;
  private Fixture fixture;

  @BeforeEach
  public void setup()
  {
    fixture = new Fixture();
  }

  @Test
  public void test_givenProductCodeAndProductQuantity_whenPassedInAMethodItemTotalPrice_thenReturnTotalPrice()
  {
    fixture.givenDataHasBeenInitialized(2);
    fixture.whenProductCodeAndProductQuantityHasBeenPassedInAMethodItemTotalPrice(2, 2);
    fixture.thenResultShouldBeTotalPrice(160);
  }

  @Test
  public void test_givenWrongProductCodeAndProductQuantity_whenPassedInAMethodItemTotalPrice_thenReturnNoProduct()
  {
    fixture.givenDataHasBeenInitialized(8);
    fixture.whenWrongProductCodeAndProductQuantityHasBeenPassedInAMethodItemTotalPrice(8, 2);
    fixture.thenResultShouldBeZero(0);
  }

  @Test
  public void test_givenProductCodeAndNegativeProductQuantity_whenPassedInAMethodItemTotalPrice_thenReturnNoProduct()
  {
    fixture.givenDataHasBeenInitialized(8);
    fixture.whenProductCodeAndNegativeProductQuantityHasBeenPassedInAMethodItemTotalPrice(2, 0);
    fixture.thenResultShouldBeZero(0);
  }


  private static class Fixture
  {
    private LineItem lineItemTestClass = new LineItem();
    private double result;

    private Product product;

    public void givenDataHasBeenInitialized(int productCode)
    {
      this.product = new Product(productCode);
      lineItemTestClass.setProduct(product);
    }

    public void whenProductCodeAndProductQuantityHasBeenPassedInAMethodItemTotalPrice(int productCode, double quantity)
    {
      result = lineItemTestClass.itemTotalPrice(productCode, quantity);
    }

    public void thenResultShouldBeTotalPrice(double totalPrice)
    {
      assertEquals(totalPrice, result);
    }

    public void whenWrongProductCodeAndProductQuantityHasBeenPassedInAMethodItemTotalPrice(int productCode, double quantity)
    {
      result = lineItemTestClass.itemTotalPrice(productCode, quantity);
    }

    public void thenResultShouldBeZero(double Zero)
    {
      assertEquals(Zero, result);
    }

    public void whenProductCodeAndNegativeProductQuantityHasBeenPassedInAMethodItemTotalPrice(int productCode, double quantity)
    {
      result = lineItemTestClass.itemTotalPrice(productCode, quantity);
    }
  }
}