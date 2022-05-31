import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

//Class for outputting to an external File
public class Output {

    private File outputFile;

    //Constructor, given the file location of the output file
    Output(String fileLocation) {
        this.outputFile = new File(fileLocation);
    }

    //Prints messages along with the time of the messages to the output file
    public void update(String message) {
        try {
            FileWriter outputFileWriter = new FileWriter(this.outputFile, true);
            PrintWriter outputPrintWriter = new PrintWriter(outputFileWriter);

            outputPrintWriter.append("\n\n");
            outputPrintWriter.append(LocalDateTime.now().toString());
            outputPrintWriter.append("\n");
            outputPrintWriter.append(message);
            outputPrintWriter.append("\n\n");
            outputPrintWriter.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    
    
}
