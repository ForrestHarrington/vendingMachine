import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.*;


//Class to handle parsing JSON files, TODO Singleton Pattern
public class JsonParser {
    
    private JSONObject myObj;

    //Constructor
    JsonParser(String inputFile) {
        inputJson(inputFile);
    }

    //Input new JSON file to be active in the JSON reader class
    public void inputJson(String inputFile) {
        try {
            Object obj = new JSONParser().parse(new FileReader(inputFile));
            this.myObj = (JSONObject) obj;   
        }
        catch (IOException|ParseException e){
            e.getStackTrace();
        }
    }

    //Parse the config arguments
    public Config getConfig() {

        JSONObject conf = (JSONObject) this.myObj.get("config");
        long x = (long) conf.get("rows");
        String yString = (String) conf.get("columns");
        int y = Integer.parseInt(yString);


        System.out.println("X config : " + x);
        System.out.println("Y config : " + y);

        return new Config((int)x,(int)y);
        
    }

    //Parse all the item arguments
    public Product[] getItems() {

        JSONArray items = (JSONArray) this.myObj.get("items"); 

        List<Product> products = new ArrayList<Product>();


        for(int i = 0; i < items.size(); i++) {
            products.add(new Product((String)((JSONObject)items.get(i)).get("name"), 
            Float.parseFloat(((String)((JSONObject)items.get(i)).get("price")).substring(1)), 
            (int)((long)((JSONObject)items.get(i)).get("amount"))));
        }
    
        
        Product[] prodArray = new Product[items.size()];

        System.out.println(products.toString());
        
        return products.toArray(prodArray);
        
    }



}
