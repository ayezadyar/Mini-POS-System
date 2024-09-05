package org.miniPosSystem;

import org.json.JSONArray;
import org.json.JSONObject;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

public class DatabaseManager
{

  public HashMap<Integer, JSONObject> loadDataFromJson(String filePath,String key)
  {
    HashMap<Integer, JSONObject> dataMap = new HashMap<>();

    try
    {
      // Read the JSON file as a String
      String content = new String(Files.readAllBytes(Paths.get(filePath)));

      // Parse the content into a JSONObject
      JSONObject jsonObject = new JSONObject(content);

      // Get the JSONArray for the "products" key
      JSONArray dataArray = jsonObject.getJSONArray(key);

      // Loop through each element in the JSONArray
      for (int i = 0; i < dataArray.length(); i++)
      {
        // Get each element as a JSONObject
        JSONObject data = dataArray.getJSONObject(i);

        // Get the product code as the key
        int Code = data.getInt("Code");

        // Put the product object in the HashMap with the productCode as the key
        dataMap.put(Code, data);
      }

    }
    catch (Exception e)
    {
      e.printStackTrace();
    }

    return dataMap;
  }


}
