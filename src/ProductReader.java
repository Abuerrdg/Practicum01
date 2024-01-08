import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
public class ProductReader {
        public static void main(String[] args) {
            // Specify the path to the directory containing your ProductTestData.txt file
            String initialDirectoryPath = "C:\\Users\\daria\\PersonGenerator\\src";

            // Create a File object for the initial directory
            File initialDirectory = new File(initialDirectoryPath);

            JFileChooser fileChooser = new JFileChooser(initialDirectory);
            fileChooser.setDialogTitle("Select a Product file");
            fileChooser.setFileFilter(new FileNameExtensionFilter("Text Files", "txt"));

            int userSelection = fileChooser.showOpenDialog(null);
            if (userSelection == JFileChooser.APPROVE_OPTION) {
                try {
                    String filePath = fileChooser.getSelectedFile().getAbsolutePath();
                    displayProductFile(filePath);
                } catch (IOException e) {
                    System.out.println("Error reading the file: " + e.getMessage());
                }
            } else {
                System.out.println("File selection cancelled by the user.");
            }
        }

        private static void displayProductFile(String filePath) throws IOException {
            System.out.println("ID#        Name          Description                Cost");
            System.out.println("====================================================");

            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(", ");
                    String formattedLine = String.format("%-10s %-13s %-25s $%.2f",
                            parts[0], parts[1], parts[2], Double.parseDouble(parts[3]));
                    System.out.println(formattedLine);
                }
            }
        }
}

