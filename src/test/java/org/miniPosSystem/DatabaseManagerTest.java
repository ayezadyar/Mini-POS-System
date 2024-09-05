package org.miniPosSystem;

import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.*;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

public class DatabaseManagerTest
{
  private Fixture fixture;

  @BeforeEach
  public void setup()
  {
    fixture = new Fixture();
  }

  @Test
  public void test_givenProductCode_WhenPassedInAMethodLoadDataFromJson_thenReturnProductName()
  {
    fixture.givenProductDataHasBeenInitialized();
    fixture.whenProductCodeHasBeenPassedInAMethod(2);
    fixture.thenResultShouldBeProductName("ColdDrink");
  }

  @Test
  public void test_givenProductCode_whenPassedInAMethodLoadDataFromJson_thenReturnProductPrice()
  {
    fixture.givenProductDataHasBeenInitialized();
    fixture.whenProductCodeHasBeenPassedInAMethod(1);
    fixture.thenResultShouldBeProductPrice(25);
  }

  @Test
  public void test_givenWrongProductCode_whenPassedInAMethodLoadDataFromJson_thenReturnZero()
  {
    fixture.givenProductDataHasBeenInitialized();
    fixture.whenWrongProductCodeHasBeenPassedInAMethod(5);
    fixture.thenResultShouldBeZero(0);

  }

  @Test
  public void test_givenTenderCode_whenPassedInAMethodLoadDataFromJson_thenReturnTenderType()
  {
    fixture.givenTenderDataHasBeenInitialized();
    fixture.whenTenderCodeHasBeenPassedInAMethod(2);
    fixture.thenResultShouldBeTenderType("Card");
  }

  @Test
  public void test_givenWrongTenderCode_whenPassedInAMethodLoadDataFromJson_thenReturnTenderType()
  {
    fixture.givenTenderDataHasBeenInitialized();
    fixture.whenWrongTenderCodeHasBeenPassedInAMethod(5);
    fixture.thenResultShouldBeZero(0);
  }

  private static class Fixture
  {
    private DatabaseManager databaseManagerTestClass = new DatabaseManager();
    private HashMap<Integer, JSONObject> productsMap = new HashMap<>();
    private HashMap<Integer, JSONObject> tendersMap = new HashMap<>();
    JSONObject productsJson = new JSONObject();
    JSONObject tendersJson = new JSONObject();


    public void givenProductDataHasBeenInitialized()
    {
      productsMap = databaseManagerTestClass.loadDataFromJson("./Product.json", "products");
    }

    public void givenTenderDataHasBeenInitialized()
    {
      tendersMap = databaseManagerTestClass.loadDataFromJson("./Tender.json", "tenders");
    }

    public void whenProductCodeHasBeenPassedInAMethod(int productCode)
    {
      productsJson = productsMap.get(productCode);
    }

    public void whenWrongProductCodeHasBeenPassedInAMethod(int productCode)
    {
      productsJson = productsMap.get(productCode);
    }

    public void thenResultShouldBeProductName(String ProductName)
    {
      assertEquals(ProductName, productsJson.getString("productName"), "Product name should be 'Cookies'");
    }

    public void thenResultShouldBeProductPrice(double ProductPrice)
    {
      assertEquals(ProductPrice, productsJson.getDouble("productPrice"), "Product price should be 25.0");
    }

    public void thenResultShouldBeZero(double zero)
    {
      if (productsJson == null || tendersJson == null)
      {
        System.out.println("No Data loaded");
        assertEquals(zero, 0);
      }

    }


    public void whenTenderCodeHasBeenPassedInAMethod(int tenderCode)
    {
      tendersJson = tendersMap.get(tenderCode);
    }

    public void thenResultShouldBeTenderType(String tenderType)
    {
      assertEquals(tenderType, tendersJson.getString("tenderType"), "Product name should be 'Card'");
    }

    public void whenWrongTenderCodeHasBeenPassedInAMethod(int tenderCode)
    {
      tendersJson = tendersMap.get(tenderCode);
    }
  }
}
