import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;

public class PersonReader {
    public static void main(String[] args) {
        // Specify the path to the directory containing your personData.txt file
        String initialDirectoryPath = "C:\\Users\\daria\\PersonGenerator\\src";

        // Create a File object for the initial directory
        File initialDirectory = new File(initialDirectoryPath);

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(initialDirectory);
        fileChooser.setDialogTitle("Select a Person file");
        fileChooser.setFileFilter(new FileNameExtensionFilter("Text Files", "txt"));

        int userSelection = fileChooser.showOpenDialog(null);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            try {
                String filePath = fileChooser.getSelectedFile().getAbsolutePath();
                displayPersonFile(filePath);
            } catch (IOException e) {
                System.out.println("Error reading the file: " + e.getMessage());
            }
        } else {
            System.out.println("File selection cancelled by the user.");
        }
    }

    private static void displayPersonFile(String filePath) throws IOException {
        System.out.println("ID#        Firstname     Lastname      Title     YOB");
        System.out.println("=============================================");

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(", ");
                String formattedLine = String.format("%-10s %-13s %-13s %-8s %s",
                        parts[0], parts[1], parts[2], parts[3], parts[4]);
                System.out.println(formattedLine);
            }
        }
    }
}