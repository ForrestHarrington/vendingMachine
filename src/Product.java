//represents a single type of product, with an amount and a price 
public class Product {

    //members of a product are the name, price and quantity, you may change the 
    //price or amount of an item but you cannot change the name, that would be a different product
    private final String name;
    private float cost;
    private int amount;

    //Constructor
    Product(String name, float cost, int amount) {
        this.name = name;
        this.cost = cost;
        this.amount = amount;
    }

    //New equality check for custom object
    @Override
    public boolean equals(Object obj) {
        if(obj == this) {
            return true;
        }
        else if(! (obj instanceof Product)) {
            return false;
        }
        else {
            Product other = (Product) obj;
            return this.name.equals(other.getName());
        }
    }

    //Reduces quantity of item when purchased
    public void itemTaken() {
        this.amount -= 1;
    }

    //For adding new items based on new JSON object (not fully implemented elsewhere)
    public void itemsAdded(int added) {
        this.amount += added;
    }

    //getters and setters
    public String getName() {
        return this.name;
    }

    public float getCost() {
        return this.cost;
    }

    public void setCost(float newCost) {
        this.cost = newCost;
    }

    public int getAmount() {
        return this.amount;
    }

    public void setAmount(int newAmount) {
        this.amount = newAmount;
    }

}