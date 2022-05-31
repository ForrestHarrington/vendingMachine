
public class VendingMachine {

    private int rows;
    private int collumns;
    private Product[][] items;

    //class for representing the state of the whole vending machine
    VendingMachine(int rows, int collumns, Product[] items) {
        this.rows = rows;
        this.collumns = collumns;
        this.items = new Product[rows][collumns];
        this.populateItems(items);
    }

    //For new JSON inputs to re-establish the configuration of the vending machine
    public void resizeVendingMachine(int rows, int collumns) {

        if(rows < this.rows || collumns < this.collumns) {
            System.out.println("WARNING : Shrinking the size of the vending machine will lead to a loss of the products currently in the vending machine that cannot fit into the new sized vending machine");
        }

        //packs all the current elements into a single array
        Product[] items = new Product[this.rows * this.collumns];
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < collumns; j++) {
                items[(i * this.collumns) + j] = this.items[i][j];
            }
        }

        this.rows = rows;
        this.collumns = collumns;
        this.items = new Product[rows][collumns];

        this.populateItems(items);

    }

    //pass in all items and populate the rows and columns in order
    public void populateItems(Product[] items) {

        for(int i = 0; i < items.length; i++) {
            this.items[(int)(i / this.collumns)][i % this.collumns] = items[i];
        }

    }
    //Find and produce the item at a particular point, based on expected user input
    public Product getProductAt(char row, int collumn) throws Exception {
        int convertedRow;
        try {
            convertedRow = convertLetterToInt(row);
        } catch (Exception e) {
            throw new Exception("Invalid Input character");
        }

        //adjust for zero indexing of arrays 
        collumn -= 1;
        convertedRow -= 1;

        if(collumn > this.collumns) {
            throw new Exception("Collumn input higher than collumns");
        }
        else if(collumn < 0) {
            throw new Exception("Collumn input below 1");
        }
        else if(convertedRow > this.rows) {
            throw new Exception("Row input higher than rows");
        }
        else if(convertedRow < 0) {
            throw new Exception("Row input less than A");
        }
        else {
            return this.items[convertedRow][collumn];
        }

    }
    //helper function to convert row characters to their respective integer values
    private int convertLetterToInt(char letter) throws Exception {
        char standardLetter = Character.toUpperCase(letter);
        int converted = (int) standardLetter;
        if(converted >= 65 && converted <= 90) {
            return converted - 64;
        }
        else  {
            throw new Exception("Invalid Input character");
        }
    }
    //display the state of the vending machine, for debugging and UI
    public void display() {
        for(int i = 0; i < this.rows; i++) {
            for(int j = 0; j < this.collumns; j++) {
                System.out.print((j+1) +""+ (char)(i+65) + ". ");
                if(this.items[i][j] != null) {
                    System.out.print(this.items[i][j].getName() + " ");
                    System.out.print(this.items[i][j].getAmount() + " ");
                    System.out.print("$" + this.items[i][j].getCost());
                }
                else {
                    System.out.print("Empty");
                }
                System.out.print(" | ");
            }
            System.out.println("\n");
        }
    }

    



}