import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
public class ProductWriter {
    public static void main(String[] args) {
        Boolean done = false;
        ArrayList<String> products = new ArrayList<>();
        Scanner in = new Scanner(System.in);

        do {
            String ID = SafeInput.getNonZeroLenString(in, "Enter the ID [6 digits]: ");
            String name = SafeInput.getNonZeroLenString(in, "Enter the product name: ");
            String description = SafeInput.getNonZeroLenString(in, "Enter the description: ");
            double cost = SafeInput.getDouble(in, "Enter the cost: ");

            String productRec = ID + ", " + name + ", " + description + ", " + cost;
            products.add(productRec);

            done = SafeInput.getYNConfirm(in, "Are you done? ");
        } while (!done);

        String fileName = "ProductTestData.txt"; // File name to save the records
        saveProductsToFile(fileName, products);
    }

    private static void saveProductsToFile(String fileName, ArrayList<String> products) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (String product : products) {
                writer.write(product);
                writer.newLine();
            }
            System.out.println("Product data has been written to " + fileName);
        } catch (IOException e) {
            System.out.println("Error writing to the file: " + e.getMessage());
        }
    }
}
