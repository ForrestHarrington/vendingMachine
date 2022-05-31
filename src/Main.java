import java.util.*;

public class Main {

    public static void main(String[] args) {

        //Create out object for printing what is going on to an external file
        Output myOut = new Output("output/output.txt");

        //handle building our JSON reading object
        JsonParser myParser = new JsonParser("input/input.json");
        Config myConfig = myParser.getConfig();
        Product[] myItems = myParser.getItems();

        //Build our actual Vending machine
        VendingMachine myVending = new VendingMachine(myConfig.getX(), myConfig.getY(), myItems);
        myVending.display();

        //Holds wether or not we should continue looping for user input
        boolean running = true;

        //Input scanner
        Scanner myScanner = new Scanner(System.in);
        String userInputs;
        Product chosen = null;

        //main loop
        while(running) {
            System.out.println("Welcome, What would you like? Please input the Character followed by the Digit of the item you would like.");
            userInputs = myScanner.nextLine();

            if(userInputs.equals("q")) {
                running = false;
                break;
            }
            else if(userInputs.length() == 2) {
                try {
                    chosen = myVending.getProductAt(userInputs.charAt(1), Integer.parseInt(userInputs.substring(0, 1)));  
                    myOut.update("Buyer has chosen " + chosen.getName());             
                }
                catch (Exception e) {
                    System.out.println("Illegal Inputs");
                }
            }

            if(chosen != null && chosen.getAmount() > 0 ) {
                System.out.print("That will be $" + chosen.getCost() + " input payment : ");
                myOut.update("Buyer must pay " + chosen.getCost()); 
            }
            else {
                System.out.println("I'm afraid we are out of that item");
            }

            userInputs = myScanner.nextLine();

            myOut.update("Buyer pays " + Float.parseFloat(userInputs)); 

            if(chosen.getCost() <= Float.parseFloat(userInputs)) {
                System.out.println("Successful Purchase, your change is " +  (chosen.getCost() - Float.parseFloat(userInputs)));
                myOut.update("Successful buy with change  " + (chosen.getCost() - Float.parseFloat(userInputs))); 
                chosen.itemTaken();
            }
            else {
                System.out.println("Unsuccessful payment");
                myOut.update("Insufficient Payment"); 
            }

            
            myVending.display();

        }

        myScanner.close();

    }
    
}
