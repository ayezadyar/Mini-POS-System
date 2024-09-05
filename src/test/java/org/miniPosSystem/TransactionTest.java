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
    fixture.whenMultipleProductCodePassedInAAddLineItemMethod(1, 2);
    fixture.thenResultShouldBeProductAndItsTotalPriceForMultipleProducts(1, "Cookies", 125);
    fixture.thenResultShouldBeProductAndItsTotalPriceForMultipleProducts(2, "ColdDrink", 400);
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
  public void test_givenOneProduct_whenPassedInAddLineItemMethod_thenReturnItsTotalPrice()
  {
    fixture.whenOneProductCodePassedInAAddLineItemMethod(1);
    fixture.thenResultShouldBeItsTotalPrice(125);
  }

  @Test
  public void test_givenMultipleProduct_whenPassedInAddLineItemMethod_thenReturnItsTotalPrice()
  {
    fixture.whenOneProductCodePassedInAAddLineItemMethod(1, 2, 3, 1);
    fixture.thenResultShouldBeItsTotalPrice(800);
  }

  @Test
  public void test_givenWrongMultipleProduct_whenPassedInAddLineItemMethod_thenReturnTotalPriceWithoutWrongProduct()
  {
    fixture.whenOneProductCodePassedInAAddLineItemMethod(1, 2, 33, 1);
    fixture.thenResultShouldBeItsTotalPrice(650);
  }

  @Test
  public void test_givenTenderTypeAndPaymentAmount_whenCreateTenderMethodIsCalled_thenReturnTenderAndAmount()
  {
    fixture.whenAddLineMethodIsCalled(1); //quantity = 5 totalPrice = 125
    fixture.whenTenderTypePassedInACreateTenderTypeMethod("Cash", 125);
    fixture.thenResultShouldCreateATenderAsTenderTypeAndTotalPaymentAmount("Cash", 125);
  }

  @Test
  public void test_givenMultipleTenderTypeAndPaymentAmount_whenCreateTenderMethodIsCalled_thenReturnTenderAndAmount()
  {
    fixture.whenAddLineMethodIsCalled(1, 2); //quantity = 5 totalPrice = 525
    fixture.whenMultipleTenderTypePassedInAAddTenderTypeMethod("cash", 100);
    fixture.whenMultipleTenderTypePassedInAAddTenderTypeMethod("card", 200);
    fixture.whenMultipleTenderTypePassedInAAddTenderTypeMethod("voucher", 225);
    fixture.thenResultShouldCreateMultipleTenderAsTenderTypeAndTotalPaymentAmount("cash", 125);
  }

  @Test
  public void test_givenProductCodeWithQuantityAndValuePromotionCode_whenApplyPromotionMethodIsCalled_thenReturnExpectedPriceAfterApplyingPromotion()
  {
    fixture.givenPromotionDataHasBeenInitialized(1, 6, 3); // productPrice = 25  totalPrice = 150
    fixture.whenApplyPromotionMethodIsCalled();
    fixture.thenResultShouldBeAppliedPromotion(100);
  }

  @Test
  public void test_givenProductCodeWithQuantityAndPercentagePromotionCode_whenApplyPromotionMethodIsCalled_thenReturnExpectedPriceAfterApplyingPromotion()
  {
    fixture.givenPromotionDataHasBeenInitialized(1, 6, 1); // productPrice = 25  totalPrice = 150
    fixture.whenApplyPromotionMethodIsCalled();
    fixture.thenResultShouldBeAppliedPromotion(135);
  }

  @Test
  public void test_givenProductCodeWithLessQuantityAndPercentageCode_whenApplyPromotionMethodIsCalled_thenReturnExpectedPriceAfterApplyingPromotion()
  {
    fixture.givenPromotionDataHasBeenInitialized(2, 4, 1); // productPrice = 80  totalPrice = 320
    fixture.whenApplyPromotionMethodIsCalled();
    fixture.thenResultShouldBeAppliedPromotion(320);
  }

  @Test
  public void test_givenProductCodeWithQuantityAndWrongPromotionCode_whenApplyPromotionMethodIsCalled_thenReturnExpectedPriceAfterApplyingPromotion()
  {
    fixture.givenPromotionDataHasBeenInitialized(2, 5, 22); // productPrice = 80  totalPrice = 320
    fixture.whenApplyPromotionMethodIsCalled();
    fixture.thenResultShouldBeAppliedPromotion(400);
  }

  @Test
  public void test_givenMultipleProductCodeWithQuantityAndValuePromotionCode_whenApplyPromotionMethodIsCalled_thenReturnExpectedPriceAfterApplyingPromotion()
  {
    fixture.givenMultipleProductsAndPromotionDataHasBeenInitialized();
    fixture.whenApplyPromotionMethodForMultipleProductsIsCalled();
    fixture.thenResultShouldBeAppliedPromotion(0);
  }


  private static class Fixture
  {
    private Transaction transactionTestClass = new Transaction();
    private LineItem lineItem;
    private ArrayList<LineItem> result;
    private Tender tender;
    private ArrayList<Tender> tenderList;
    Tender res;
    double subtractedValue;
    private ArrayList<Promotion> promotionList;
    private Promotion promotionValue;


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

    public void whenMultipleProductCodePassedInAAddLineItemMethod(int... productCodes)
    {

      transactionTestClass.addLineItem(productCodes[0], 5);
      transactionTestClass.addLineItem(productCodes[1], 5);

    }

    public void whenOneProductCodePassedInAAddLineItemMethod(int... productCodes)
    {
      for (int code : productCodes)
      {
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

    public void thenResultShouldBeTenderType(String tenderType)
    {
      assertEquals(tenderType, res);

    }


    public void thenResultShouldbeSubtractedValue(int i)
    {
      assertEquals(i, subtractedValue);
    }

    public void whenAddLineMethodIsCalled(int... productCodes)
    {
      for (int code : productCodes)
      {
        transactionTestClass.addLineItem(code, 5);
      }
    }

    public void whenTenderTypePassedInACreateTenderTypeMethod(String tenderType, double paymentAmount)
    {
      tender = transactionTestClass.createTender(tenderType, paymentAmount);
    }

    public void thenResultShouldCreateATenderAsTenderTypeAndTotalPaymentAmount(String tenderType, double paymentAmount)
    {
      assertEquals(tenderType, tender.getTenderTypes());
      assertEquals(paymentAmount, tender.getPaymentAmount());
    }


    public void whenMultipleTenderTypePassedInAAddTenderTypeMethod(String tenderType, double paymentAmount)
    {
      transactionTestClass.addTender(tenderType, paymentAmount);

    }

    public void thenResultShouldCreateMultipleTenderAsTenderTypeAndTotalPaymentAmount(String tenderType, double paymentAmount)
    {
      ArrayList<Tender> items = transactionTestClass.getTenderList();
      boolean result = false;
      for (Tender line : items)
      {
        if (line.getTenderTypes() == tenderType)
        {
          result = true;
          break;
        }
      }
      assertEquals(true, result);
    }


    public void givenPromotionDataHasBeenInitialized(int productCode, double quantity, int promotionCode)
    {
      transactionTestClass.addLineItem(productCode, quantity);
      promotionList = new ArrayList<>();
      promotionValue = new Promotion(promotionCode);
      promotionList.add(promotionValue);

    }

    public void whenApplyPromotionMethodForMultipleProductsIsCalled()
    {
      transactionTestClass.applyPromotions(promotionList);

      transactionTestClass.addTender("Cash", 200);
      transactionTestClass.addTender("Card", 100);
      transactionTestClass.addTender("Voucher", 95);
    }

    public void thenResultShouldBeAppliedPromotion(double expectedTotal)
    {

      assertEquals(expectedTotal, transactionTestClass.getTotalPrice(), 0.01);

    }

    public void givenMultipleProductsAndPromotionDataHasBeenInitialized()
    {
      transactionTestClass.addLineItem(1, 5);
      transactionTestClass.addLineItem(2, 4);

      promotionList = new ArrayList<>();
      promotionValue = new Promotion(3);
      promotionList.add(promotionValue);


    }

    public void whenApplyPromotionMethodIsCalled()
    {
      transactionTestClass.applyPromotions(promotionList);
    }
  }
}